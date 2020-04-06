package com.lwhtarena.elasticsearch.low;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.Node;
import org.elasticsearch.client.NodeSelector;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @author liwh
 * @Title: Initialization
 * @Package com.lwhtarena.elasticsearch.low
 * @Description: 初始化
 * @Version 1.0.0
 * @date 2020/4/6 13:26
 */
public class Initialization {

    static String IP ="192.168.31.120";
    static Integer PORT =9200;
    static String PROTOCOL ="http";


    /**
     * 构造实例
     */
    @Test
    public void test001() throws IOException {
        RestClient restClient =RestClient.builder(
                new HttpHost(IP,PORT,PROTOCOL),
                new HttpHost("localhost",9201,"http"))
                .build();

        //业务操作

        restClient.close();
    }

    /**
     * 设置参数
     */
    @Test
    public void test002(){
        RestClientBuilder builder =RestClient.builder(new HttpHost(IP,PORT,PROTOCOL));
        /**
         * 设置每个请求都需要发送的默认标头，以防止必须在每个单个请求中指定他们
         */
        Header[] defaultHeader =new Header[]{new BasicHeader("header","value")};
        builder.setDefaultHeaders(defaultHeader);

        /**
         * 设置每个监听器，该监听器在每次节点发生故障得到通知，以防需要采取措施。启用嗅探失败时在内部使用
         */
        builder.setFailureListener(new RestClient.FailureListener(){
            @Override
            public void onFailure(Node node) {
                super.onFailure(node);
            }
        });
    }

    /**
     * 设置节点选择器以用于过滤客户端将向其自身发送的请求中的客户端发送的节点。例如，在启用嗅探功能时，
     * 这可以防止阻止向专用主节点发送请求。默认情况下，客户端将请求发送到每个已配置的节点。
     */
    @Test
    public void test003(){
        HttpHost[] hosts;
        RestClientBuilder builder =RestClient.builder(new HttpHost(IP,PORT,PROTOCOL));
        builder.setNodeSelector(NodeSelector.SKIP_DEDICATED_MASTERS);
    }

    /**
     * 设置允许修改默认请求配置的回调（例如，请求超时，身份验证或
     * org.apache.http.client.config.RequestConfig.Builder 允许设置的任何内容）
     */
    @Test
    public void test004(){
        RestClientBuilder builder =RestClient.builder(new HttpHost(IP,PORT,PROTOCOL));

        builder.setRequestConfigCallback(new RestClientBuilder.RequestConfigCallback() {
            @Override
            public RequestConfig.Builder customizeRequestConfig(RequestConfig.Builder requestConfigBuilder) {
                return requestConfigBuilder.setSocketTimeout(10000);
            }
        });
    }

    /**
     * 设置允许修改http客户端配置的回调（例如，通过ssl加密的通信，或
     * org.apache.http.impl.nio.client.HttpAsyncClientBuilder 允许设置的任何内容）
     */
    @Test
    public void test005(){
        RestClientBuilder builder =RestClient.builder(new HttpHost(IP,PORT,PROTOCOL));

        builder.setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
            @Override
            public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpAsyncClientBuilder) {
                return httpAsyncClientBuilder.setProxy(new HttpHost("proxy",9000,"http"));
            }
        });

    }
}
