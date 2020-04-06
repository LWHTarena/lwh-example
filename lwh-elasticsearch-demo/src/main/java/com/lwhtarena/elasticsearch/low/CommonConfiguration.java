package com.lwhtarena.elasticsearch.low;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.Node;
import org.elasticsearch.client.NodeSelector;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Iterator;

/**
 * @author liwh
 * @Title: CommonConfiguration
 * @Package com.lwhtarena.elasticsearch.low
 * @Description: 通用配置
 * @Version 1.0.0
 * @date 2020/4/6 15:47
 */
public class CommonConfiguration {

    /**
     * 超时时间
     * 通过在其构建器构建RestClient时提供RequestConfigCallback实例，可以配置请求超时。接口具有一种方法，
     * 该方法接收org.apache.http.client.config.RequestConfig.Builder的实例作为参数，并且具有相同的返
     * 回类型。可以修改请求配置构建器，然后返回。在下面的示例中，我们增加了连接超时（默认为1秒）和套接字超时
     * （默认为30秒）。
     */
    public void test001(){
        RestClientBuilder builder = RestClient.builder(
                new HttpHost("localhost", 9200))
                .setRequestConfigCallback(
                        new RestClientBuilder.RequestConfigCallback() {
                            @Override
                            public RequestConfig.Builder customizeRequestConfig(
                                    RequestConfig.Builder requestConfigBuilder) {
                                return requestConfigBuilder
                                        .setConnectTimeout(5000)
                                        .setSocketTimeout(60000);
                            }
                        });
    }

    /**
     * 线程数
     * 默认情况下，Apache Http Async Client启动一个调度程序线程，以及连接管理器使用的多个工作线程，
     * 数量与本地检测到的处理器数量相同（取决于Runtime.getRuntime（）。availableProcessors（）返
     * 回的内容）。线程数可以如下修改：
     */
    @Test
    public void test002(){
        RestClientBuilder builder = RestClient.builder(
                new HttpHost("localhost", 9200))
                .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                    @Override
                    public HttpAsyncClientBuilder customizeHttpClient(
                            HttpAsyncClientBuilder httpClientBuilder) {
                        return httpClientBuilder.setDefaultIOReactorConfig(
                                IOReactorConfig.custom()
                                        .setIoThreadCount(1)
                                        .build());
                    }
                });
    }

    /**
     * 基本身份验证编辑
     * 配置基本身份验证可以通过通过构建器构建 RestClient 时提供 HttpClientConfigCallback 来完成。该接口有一个
     * 接收 org.apache.http.impl.nio.client.httpAsyncClientBuilder 作为参数且具有相同返回类型的方法。可以修
     * 改 http 客户端生成器，然后返回。在下面的示例中，我们设置了需要基本身份验证的默认凭据提供程序。
     */
    public void test003(){
        final CredentialsProvider credentialsProvider =
                new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials("user", "password"));

        RestClientBuilder builder = RestClient.builder(
                new HttpHost("localhost", 9200))
                .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                    @Override
                    public HttpAsyncClientBuilder customizeHttpClient(
                            HttpAsyncClientBuilder httpClientBuilder) {
                        return httpClientBuilder
                                .setDefaultCredentialsProvider(credentialsProvider);
                    }
                });

        /**
         * 可以禁用抢占身份验证，这意味着每个请求都将在未经授权标头的情况下发送，以查看是否接受它，并在收到
         * HTTP 401 响应后，将使用基本身份验证标头重新发送完全相同的请求。如果希望执行此操作，
         * 则可以通过 HttpAsyncClientBuilder 禁用它：
         *
         * final CredentialsProvider credentialsProvider =
         *     new BasicCredentialsProvider();
         * credentialsProvider.setCredentials(AuthScope.ANY,
         *     new UsernamePasswordCredentials("user", "password"));
         *
         * RestClientBuilder builder = RestClient.builder(
         *     new HttpHost("localhost", 9200))
         *     .setHttpClientConfigCallback(new HttpClientConfigCallback() {
         *         @Override
         *         public HttpAsyncClientBuilder customizeHttpClient(
         *                 HttpAsyncClientBuilder httpClientBuilder) {
         *             httpClientBuilder.disableAuthCaching(); //禁用先发制人身份验证
         *             return httpClientBuilder
         *                 .setDefaultCredentialsProvider(credentialsProvider);
         *         }
         *     });
         */
    }

    /**
     * 其他身份验证方法
     *
     * Elasticsearch令牌服务令牌
     * 如果您希望客户端使用Elasticsearch访问令牌进行身份验证，请设置相关的HTTP请求标头。如果客户端仅
     * 代表一个用户发出请求，则可以将必要的Authorization标头设置为默认标头，如以下示例所示：
     *      RestClientBuilder builder = RestClient.builder(
     *               new HttpHost("localhost", 9200, "http"));
     *      Header[] defaultHeaders =
     *              new Header[]{new BasicHeader("Authorization",
     *         "Bearer u6iuAxZ0RG1Kcm5jVFI4eU4tZU9aVFEwT2F3")};
     *      builder.setDefaultHeaders(defaultHeaders);
     *
     * Elasticsearch API密钥
     *
     * 如果您希望客户端使用Elasticsearch API密钥进行身份验证，请设置相关的HTTP请求标头。如果客户端仅
     * 代表一个用户发出请求，则可以将必要的Authorization标头设置为默认标头，如以下示例所示：
     */
    public void test004(){
        String apiKeyId = "uqlEyn8B_gQ_jlvwDIvM";
        String apiKeySecret = "HxHWk2m4RN-V_qg9cDpuX";
        String apiKeyAuth =
                Base64.getEncoder().encodeToString(
                        (apiKeyId + ":" + apiKeySecret)
                                .getBytes(StandardCharsets.UTF_8));
        RestClientBuilder builder = RestClient.builder(
                new HttpHost("localhost", 9200, "http"));
        Header[] defaultHeaders =
                new Header[]{new BasicHeader("Authorization",
                        "ApiKey " + apiKeyAuth)};
        builder.setDefaultHeaders(defaultHeaders);
    }

    /**
     * 加密通讯
     *
     * 还可以通过HttpClientConfigCallback配置使用TLS的加密通信。作为参数接收的
     * org.apache.http.impl.nio.client.HttpAsyncClientBuilder公开了多种配置加密通信的方法：
     * setSSLContext，setSSLSessionStrategy和setConnectionManager（从最低优先级开始按优先级排序）。
     *
     * 当访问在HTTP层上为TLS设置的Elasticsearch集群时，客户端需要信任Elasticsearch使用的证书。以下是
     * 在PKCS＃12密钥库中可用CA证书时，将客户端设置为信任已签署Elasticsearch使用的证书的CA的示例：
     */
    public void test005() throws KeyStoreException, NoSuchAlgorithmException {
//        Path trustStorePath = Paths.get("/path/to/truststore.p12");
//        KeyStore truststore = KeyStore.getInstance("pkcs12");
//        try (InputStream is = Files.newInputStream(trustStorePath)) {
//            truststore.load(is, keyStorePass.toCharArray());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        SSLContextBuilder sslBuilder = SSLContexts.custom()
//                .loadTrustMaterial(truststore, null);
//        final SSLContext sslContext = sslBuilder.build();
//        RestClientBuilder builder = RestClient.builder(
//                new HttpHost("localhost", 9200, "https"))
//                .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
//                    @Override
//                    public HttpAsyncClientBuilder customizeHttpClient(
//                            HttpAsyncClientBuilder httpClientBuilder) {
//                        return httpClientBuilder.setSSLContext(sslContext);
//                    }
//                });

        /**
         * 以下是一个示例，当该CA证书可用作PEM编码文件时，将客户端设置为信任已签署
         * Elasticsearch使用的证书的CA。
         */
//        Path caCertificatePath = Paths.get("/path/to/ca.crt");
//        CertificateFactory factory =
//                CertificateFactory.getInstance("X.509");
//        Certificate trustedCa;
//        try (InputStream is = Files.newInputStream(caCertificatePath)) {
//            trustedCa = factory.generateCertificate(is);
//        }
//        KeyStore trustStore = KeyStore.getInstance("pkcs12");
//        trustStore.load(null, null);
//        trustStore.setCertificateEntry("ca", trustedCa);
//        SSLContextBuilder sslContextBuilder = SSLContexts.custom()
//                .loadTrustMaterial(trustStore, null);
//        final SSLContext sslContext = sslContextBuilder.build();
//        RestClient.builder(
//                new HttpHost("localhost", 9200, "https"))
//                .setHttpClientConfigCallback(new HttpClientConfigCallback() {
//                    @Override
//                    public HttpAsyncClientBuilder customizeHttpClient(
//                            HttpAsyncClientBuilder httpClientBuilder) {
//                        return httpClientBuilder.setSSLContext(sslContext);
//                    }
//                });

        /**
         * 当 Elasticsearch 配置为需要客户端 TLS 身份验证时（例如配置 PKI 域时），客户端需要在
         * TLS 握手期间提供客户端证书才能进行身份验证。下面是使用存储在 PKCS_12 密钥存储中的证书
         * 和私钥为 TLS 身份验证设置客户端的示例。
         */
//        Path trustStorePath = Paths.get("/path/to/your/truststore.p12");
//        Path keyStorePath = Paths.get("/path/to/your/keystore.p12");
//        KeyStore trustStore = KeyStore.getInstance("pkcs12");
//        KeyStore keyStore = KeyStore.getInstance("pkcs12");
//        try (InputStream is = Files.newInputStream(trustStorePath)) {
//            trustStore.load(is, trustStorePass.toCharArray());
//        }
//        try (InputStream is = Files.newInputStream(keyStorePath)) {
//            keyStore.load(is, keyStorePass.toCharArray());
//        }
//        SSLContextBuilder sslBuilder = SSLContexts.custom()
//                .loadTrustMaterial(trustStore, null)
//                .loadKeyMaterial(keyStore, keyStorePass.toCharArray());
//        final SSLContext sslContext = sslBuilder.build();
//        RestClientBuilder builder = RestClient.builder(
//                new HttpHost("localhost", 9200, "https"))
//                .setHttpClientConfigCallback(new HttpClientConfigCallback() {
//                    @Override
//                    public HttpAsyncClientBuilder customizeHttpClient(
//                            HttpAsyncClientBuilder httpClientBuilder) {
//                        return httpClientBuilder.setSSLContext(sslContext);
//                    }
//                });


        /**
         * 如果客户端证书和密钥在密钥库中不可用，而在PEM编码文件中可用，则不能直接使用它们来构建SSLContext。
         * 您必须依靠外部库将PEM密钥解析为PrivateKey实例。另外，您可以使用外部工具从您的PEM文件构建密钥库，如以下示例所示：
         *
         * openssl pkcs12 -export -in client.crt -inkey private_key.pem \
         *         -名称“客户端” -out client.p12
         * 如果未提供显式配置，则将使用系统默认配置。
         */
    }

    /**
     * 节点选择器
     *
     * 客户端以循环方式将每个请求发送到已配置的节点之一。可以选择通过初始化客户端时需要提供的节点选择器来过滤节
     * 点。启用嗅探功能时，如果只有专用主节点受到HTTP请求的攻击，此功能将非常有用。对于每个请求，客户端将运行最
     * 终配置的节点选择器以筛选候选节点，然后从其余请求中选择列表中的下一个。
     *
     * 不一致选择节点集的节点选择器将使轮询行为不可预测，甚至可能不公平。上面的首选项示例很好，因为它会影响已经影响
     * 循环的可预测性的节点可用性。节点选择不应取决于其他外部因素，否则轮询将无法正常工作。
     */
    @Test
    public void test006(){
        RestClientBuilder builder = RestClient.builder(
                new HttpHost("localhost", 9200, "http"));
        builder.setNodeSelector(new NodeSelector() {
            /***
             * 设置一个知道分配的节点选择器，该选择器允许在本地机架中选择一个节点（如果有），否则可以转到任何机架中的任何
             * 其他节点。考虑到如果没有本地节点可用，它将转到另一个机架，因此它是首选而不是严格的要求，而不是在这种情况下
             * 不返回任何节点，这将使客户端在任何节点都不存在时强制恢复本地节点可从首选机架中获得。
             * @param nodes
             */
            @Override
            public void select(Iterable<Node> nodes) {
                /*
                 * Prefer any node that belongs to rack_one. If none is around
                 * we will go to another rack till it's time to try and revive
                 * some of the nodes that belong to rack_one.
                 */
                boolean foundOne = false;
                for (Node node : nodes) {
                    String rackId = node.getAttributes().get("rack_id").get(0);
                    if ("rack_one".equals(rackId)) {
                        foundOne = true;
                        break;
                    }
                }
                if (foundOne) {
                    Iterator<Node> nodesIt = nodes.iterator();
                    while (nodesIt.hasNext()) {
                        Node node = nodesIt.next();
                        String rackId = node.getAttributes().get("rack_id").get(0);
                        if ("rack_one".equals(rackId) == false) {
                            nodesIt.remove();
                        }
                    }
                }
            }
        });


    }

}
