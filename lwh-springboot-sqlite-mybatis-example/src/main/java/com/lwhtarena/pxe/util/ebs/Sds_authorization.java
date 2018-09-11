package com.lwhtarena.pxe.util.ebs;

import com.lwhtarena.pxe.util.HMACSHA1;
import org.apache.commons.codec.binary.Base64;

/**
 * @author： liwh
 * @Date: 2017/3/27.
 * @Description：<p>SDS-AUTHORIZATION生成算法</P>
 */
public class Sds_authorization {

    /*AccessKey 默认值*/
    private static String AccessKey ="OGE0OTU3ZWY0YWUxOTM2OGMxOTk1YzY0ZTc5ZGIy";//	OGE0OTU3ZWY0YWUxOTM2OGMxOTk1YzY0ZTc5ZGIy

    /*SecretKey 默认值为*/
    private static String SecretKey ="MTQ1MDU5NTI1NzgwYmE1MDdlMjA3OGY5MGMzNGMx";//  MTQ1MDU5NTI1NzgwYmE1MDdlMjA3OGY5MGMzNGMx

    public static String getSDS_AUTHORIZATION(long time, String uri){
        String SDS_AUTHORIZATION ="";
        /*步骤一 该次请求的生命期、HTTP 请求 URI 合并成字符串，作为令牌*/
        String _tokenURI =generateToken(time,uri);

        /*步骤二 将字符串令牌编码成 base64 安全编码*/
        String _base64_tokenURI =en64(_tokenURI);

        /*步骤三 通过 SecretKey 对令牌安全编码进行 hmac-sha1 签名*/
        String _security_signature=hmac_sha1(_base64_tokenURI,SecretKey);


        /*步骤四 将签名编码成 base64 安全编码*/
        String _base64_signature =en64(_security_signature);

        /*步骤五 将 AccessKey、签名安全编码、令牌安全编码用“$”符号做分隔符连接成字符串，得到该次请求的 SDS-AUTHORIZATION*/
        SDS_AUTHORIZATION =stitching(AccessKey,_base64_signature,_base64_tokenURI);

        return SDS_AUTHORIZATION;
    }

    /**
     * 步骤一 该次请求的生命期、HTTP 请求 URI 合并成字符串，作为令牌
     * @param time 该次请求的生命期
     * @param uri HTTP 请求 URI
     * @return
     */
    public static String generateToken(long time, String uri){
        String str ="";
        TokenURI token =new TokenURI(time,uri);
        str =token.toString();
        return str;
    }

    /**
     * 将字符串令牌编码成 base64 安全编码
     * @param tokenURI 字符串令牌
     * @return
     */
    public static String en64(String tokenURI) {
        Base64 base64 = new Base64();
        String en_str =base64.encodeAsString(tokenURI.getBytes());
        return en_str;
    }

    /**
     * 通过 SecretKey 对令牌安全编码进行 hmac-sha1 签名
     * @param _base64_tokenURI
     * @param secretKey
     * @return
     */
    private static String hmac_sha1(String _base64_tokenURI, String secretKey) {
        String signature ="";
        try {
            signature = HMACSHA1.getSignature(_base64_tokenURI,SecretKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return signature;
    }

    /**
     *将 AccessKey、签名安全编码、令牌安全编码用“$”符号做分隔符连接成字符串，得到该次请求的 SDS-AUTHORIZATION
     * @param accessKey
     * @param base64_signature 签名安全编码
     * @param base64_tokenURI 令牌安全编码
     * @return
     */
    public static String stitching(String accessKey, String base64_signature, String base64_tokenURI) {

        return accessKey+"$"+base64_signature+"$"+base64_tokenURI;
    }


    public static void main(String[] args) {
        String string =Sds_authorization.getSDS_AUTHORIZATION( 1489591808,"/api/v1/sds/cluster/version/query");
        System.out.println(string);
    }
}
