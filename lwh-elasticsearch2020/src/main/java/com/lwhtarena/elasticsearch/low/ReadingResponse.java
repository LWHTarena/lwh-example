package com.lwhtarena.elasticsearch.low;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.RequestLine;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @author liwh
 * @Title: ReadingResponse
 * @Package com.lwhtarena.elasticsearch.low
 * @Description: 处理响应
 * @Version 1.0.0
 * @date 2020/4/6 15:19
 */
public class ReadingResponse {

    static String IP ="192.168.31.120";
    static Integer PORT =9200;
    static String PROTOCOL ="http";

    /**
     * 由同步performRequest方法返回的Response对象或作为ResponseListener＃onSuccess（Response）中
     * 的参数接收的Response对象包装由http客户端返回的响应对象，并提供一些其他信息。
     *
     * 执行请求时，在以下方案中引发异常（或在响应Listener_ononon（异常）中作为参数接收：*
     *
     * IO 例外
     *      通信问题（例如套接字超时异常）
     * 响应异常。
     *  已返回响应，但其状态代码指示错误（而不是 2xx）。响应异常源自有效的 http 响应，因此它公开了相应的响应对
     * 象，该对象允许访问返回的响应。
     *
     * 对于返回 404 状态代码的 HEAD 请求，不会引发响应异常，因为它是一个预期的 HEAD 响应，仅表示找不到资源。所有
     * 其他 HTTP 方法（例如 GET）为 404 个响应引发响应异常，除非忽略参数包含 404。忽略是一个特殊的客户端参数，它
     * 不发送到 Elasticsearch，并且包含错误状态代码的逗号分隔列表。它允许控制是否应将某些错误状态代码视为预期响应，
     * 而不是异常。例如，对于 get api 非常有用，因为它可以在文档丢失时返回 404，在这种情况下，响应正文将不包含错误，
     * 而是通常的获取 api 响应，只是没有文档，因为它未找到。
     *
     * 请注意，低级客户端不会公开任何 json 编组和取消编组的帮助程序。用户可以自由地使用他们为此目的喜欢的库。
     *
     * 基础 Apache Async Http 客户端附带不同的 org.apache.http.httpEntity 实现，允许以不同格式（流、字节数组、字
     * 符串等）提供请求正文。至于读取响应正文，HttpEntity_getContent 方法非常方便，它返回来自以前缓冲的响应正文的输入
     * 流读数。作为替代方法，可以提供自定义组织组织.apache.http.nio.协议.httpAsyncResponse消费者，用于控制字节的读
     * 取和缓冲方式。
     */
    @Test
    public void test001() throws IOException {
        RestClient restClient =RestClient.builder(
                new HttpHost(IP,PORT,PROTOCOL),
                new HttpHost("localhost",9201,"http"))
                .build();

        Response response = restClient.performRequest(new Request("GET", "/"));
        /**有关已执行请求的信息**/
        RequestLine requestLine = response.getRequestLine();
        /**返回响应的主机**/
        HttpHost host = response.getHost();
        /**响应状态行，例如，您可以从中检索状态代码**/
        int statusCode = response.getStatusLine().getStatusCode();
        /**响应头，也可以通过getHeader（String）通过名称检索**/
        Header[] headers = response.getHeaders();
        /**包含在 org.apache.http.httpEntity 对象的响应正文中**/
        String responseBody = EntityUtils.toString(response.getEntity());
    }
}
