package com.lwhtarena.pxe.util.ebs;

import com.alibaba.fastjson.JSONObject;
import com.lwhtarena.pxe.util.StringUtil;
import com.lwhtarena.pxe.util.ebs.exception.EBSException;
import com.lwhtarena.pxe.util.ebs.exception.EBSExceptionJson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Date;

/**
 * @author： liwh
 * @Date: 2017/4/1.
 * @Description：<p></P>
 */
public class EBSrest implements IStrategy{

    //EBS服务IP地址
    private static String ip =null; //192.168.223.30
    public static int port=6680;
    public static String userName ="admin";
    public static String pwd ="admin";
    
    public static void setIP(String newIp){
    	ip = newIp;
    }
    
    public static String getIP(){
    	return ip;
    }

    public boolean checkIp(String ip) throws Exception {
        this.setIP(ip);
        boolean bool =false;
        CloseableHttpClient httpClient = createSSLClientDefault();
        HttpPost http = null;
        String url ="/api/v1/sds/storage/capacity";
        if(ip!=null){
            http =  new HttpPost("http://"+ ip + ":" + port + url);
        }else{
            http =  new HttpPost("http://"+ ip + ":" + port + url);
        }
        http.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
        // 读取超时
        http.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 5000    );
        http.setHeader("Accept-Encoding","gzip, deflate");
        http.setHeader("Accept","application/json, text/plain, */*");
        http.setHeader("Content-Type", "application/json");
        http.setHeader("SDS-AUTHORIZATION", Sds_authorization.getSDS_AUTHORIZATION((new Date().getTime()+10*1000),url));//permission denied

        try {
            HttpResponse httpResponse = httpClient.execute(http);
            if(httpResponse !=null){
                if (HttpStatus.SC_OK == httpResponse.getStatusLine().getStatusCode()) {
                    bool =true;
                    return bool;
                } else {
                    return bool;
                }
            }else{
                return bool;
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return bool;
        } catch (IOException e) {
            e.printStackTrace();
            return bool;
        } finally {
            try {
                if (httpClient != null) {
                    //关闭流并释放资源
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public String postRest(String url, String postBody) throws Exception {
        return null;
    }

    public  String postRest(String ip,String url,String postBody) throws Exception {
    	 return this.post(ip,url,postBody);
    }





	public static String post(String ipAddr,String url,String postBody) throws Exception {
        CloseableHttpClient httpClient = createSSLClientDefault();
        HttpPost http = null;
        if(ipAddr!=null){
        	http =  new HttpPost("http://"+ ipAddr + ":" + port + url);
        }else{
        	http =  new HttpPost("http://"+ ip + ":" + port + url);
        }
     
        http.setHeader("Accept-Encoding","gzip, deflate");
        http.setHeader("Accept","application/json, text/plain, */*");
        http.setHeader("Content-Type", "application/json");
       // http.setHeader("SDS-AUTHORIZATION", Sds_authorization.getSDS_AUTHORIZATION((new Date().getTime()+10*1000),url));//permission denied

        CloseableHttpResponse httpResponse = null;
        try {
            if (StringUtil.isNotNull(postBody)) {
                StringEntity body = new StringEntity(postBody);
                http.setEntity(body);
            }
            httpResponse = httpClient.execute(http);
            HttpEntity entity = httpResponse.getEntity();
            if (entity != null) {
                String responseBody = EntityUtils.toString(entity);
//                LogUtil.ebsAccessLog.info("使用post方法调用EBS接口，返回的结果：" + responseBody);
                if (responseBody != null && responseBody.contains("exceptionMessage")) {
                    EBSExceptionJson json = JSONObject.parseObject(responseBody, EBSExceptionJson.class);     //// 通过json工具将josn格式字符串转换成相应对象
                    throw new EBSException(json.getExceptionCode(), json.getExceptionMessage(), json.getStackTrace());
                }
                return responseBody;
            }
        }catch (Exception e){
            throw e;
        }finally {
            // 释放资源
            try {
                if (httpResponse != null) {
                    httpResponse.close();
                }
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static CloseableHttpClient createSSLClientDefault() {
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                        // 信任所有
                        public boolean isTrusted(X509Certificate[] chain,
                                                 String authType) throws CertificateException {
                            return true;
                        }
                    }).build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, new NoopHostnameVerifier());

            CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
            AuthScope authScope = new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT, AuthScope.ANY_REALM);

            // EBS访问用户名、密码
            UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(userName, pwd);

            credentialsProvider.setCredentials(authScope, credentials);

            // 使用SSLSocketFactory来创建ssl连接
            return HttpClients
                    .custom()
                    .setSSLSocketFactory(sslsf)
                    .setDefaultCredentialsProvider(credentialsProvider)
                    .build();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        return HttpClients.createDefault();
    }


}
