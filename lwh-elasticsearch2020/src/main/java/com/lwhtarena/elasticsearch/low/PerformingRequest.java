package com.lwhtarena.elasticsearch.low;

import org.junit.jupiter.api.Test;

/**
 * @author liwh
 * @Title: PerformingRequest
 * @Package com.lwhtarena.elasticsearch.low
 * @Description: 执行请求
 * @Version 1.0.0
 * @date 2020/4/6 14:26
 */
public class PerformingRequest {

    /**
     * 一旦创建RestClient，就可以通过调用performRequest或performRequestAsync发送请求。
     * performRequest是同步的，将阻塞调用线程并在请求成功时返回响应，如果请求失败则抛出异常。
     * performRequestAsync是异步的，并且接受一个ResponseListener参数，该参数在请求成功时
     * 以Response调用，如果失败则以Exception调用。
     *
     * 这是同步的：
     *         Request request = new Request(
     *                 "GET",  //The HTTP method (GET, POST, HEAD, etc)
     *                 "/");
     *         Response response = restClient.performRequest(request);
     *
     *这是异步的：
     *         Request request = new Request(
     *                 "GET",
     *                 "/");
     *         Cancellable cancellable = restClient.performRequestAsync(request,
     *                 new ResponseListener() {
     *                     @Override
     *                     public void onSuccess(Response response) {
     *                         //todo Handle the response
     *                     }
     *
     *                     @Override
     *                     public void onFailure(Exception exception) {
     *                          //todo Handle the failure 失败处理
     *                     }
     *                 });
     *
     *您可以将请求参数添加到请求对象：
     *      request.addParameter("pretty", "true");
     *
     *您可以将请求的主体设置为任何HttpEntity：
     *      request.setEntity(new NStringEntity(
     *              "{\"json\":\"text\"}",
     *              ContentType.APPLICATION_JSON));
     *
     * 注意：为HttpEntity指定的ContentType很重要，因为它将用于设置Content-Type标头，
     *  以便Elasticsearch可以正确解析内容。
     *
     *您还可以将其设置为String，该字符串将默认为application / json的ContentType。
     *      request.setJsonEntity("{\"json\":\"text\"}");
     *
     *
     */
    public void test001(){

    }

    /**
     * RequestOptions编辑
     *  RequestOptions类包含应在同一应用程序中的许多请求之间共享的部分请求。您可以创建一
     * 个单例实例，并在所有请求之间共享它：
     *
     *      private static final RequestOptions COMMON_OPTIONS;
     *      static {
     *          RequestOptions.Builder builder = RequestOptions.DEFAULT.toBuilder();
     *          builder.addHeader("Authorization", "Bearer " + TOKEN); //添加所有请求所需的headers。
     *          builder.setHttpAsyncResponseConsumerFactory( //自定义响应使用者。
     *                  new HttpAsyncResponseConsumerFactory
     *                   .HeapBufferedResponseConsumerFactory(30 * 1024 * 1024 * 1024));
     *          COMMON_OPTIONS = builder.build();
     *      }
     *
     * addHeader用于授权或使用Elasticsearch之前的代理所需的标头。无需设置Content-Type标头，因为客户端将根据附加到
     * 请求的HttpEntity自动设置标头。
     *
     * 您可以设置NodeSelector来控制哪些节点将接收请求。 NodeSelector.NOT_MASTER_ONLY是一个不错的选择。
     *
     * 您还可以自定义用于缓冲异步响应的响应使用者。默认使用者将在JVM堆上最多缓冲100MB的响应。如果响应较大，则请求将失败
     * 。例如，您可以降低最大大小，如果在上面示例中的堆受限环境中运行，可能会很有用。
     *
     * 创建单例后，可以在提出请求时使用它：
     *          request.setOptions(COMMON_OPTIONS);
     * 您还可以根据每个请求自定义这些选项。例如，这添加了一个额外的标题：
     *      RequestOptions.Builder options = COMMON_OPTIONS.toBuilder();
     *      options.addHeader("cats", "knock things off of other things");
     *      request.setOptions(options);
     */
    public void test002(){

    }

    /**
     * 多个并行异步动作
     * 客户很高兴并行执行许多动作。下面的示例并行索引许多文档。在现实世界中，您可能想使用
     * _bulk API，但该示例仅用于说明。
     *         final CountDownLatch latch = new CountDownLatch(documents.length);
     *         for (int i = 0; i < documents.length; i++) {
     *             Request request = new Request("PUT", "/posts/doc/" + i);
     *             //let's assume that the documents are stored in an HttpEntity array
     *             request.setEntity(documents[i]);
     *             restClient.performRequestAsync(
     *                     request,
     *                     new ResponseListener() {
     *                         @Override
     *                         public void onSuccess(Response response) {
     *                             //todo 处理返回的响应
     *                             latch.countDown();
     *                         }
     *
     *                         @Override
     *                         public void onFailure(Exception exception) {
     *                              // todo 由于通信错误或带有指示错误的状态代码的响应而处理返回的异常
     *                             latch.countDown();
     *                         }
     *                     }
     *             );
     *         }
     *         latch.await();
     *
     */
    @Test
    public void test003(){

    }

    /**
     * 取消异步请求
     * performRequestAsync方法返回一个Cancellable，它公开了一个称为cancel的公共方法。可以调用这
     * 种方法来取消正在进行的请求。取消请求将导致通过基础的HTTP客户端中止HTTP请求。在服务器端，这不会
     * 自动转换为取消该请求的执行，而这需要在API本身中专门实现。
     *
     * 可取消实例的使用是可选的，如果不需要，您可以放心地忽略它。一个典型的用例是将其与Rx Java或Kotlin
     * 的suspendCancellableCoRoutine等框架一起使用。取消不再需要的请求是避免对Elasticsearch施加不
     * 必要负载的好方法。
     *
     *         Request request = new Request("GET", "/posts/_search");
     *         Cancellable cancellable = restClient.performRequestAsync(
     *                 request,
     *                 new ResponseListener() {
     *                     @Override
     *                     public void onSuccess(Response response) {
     *                          //todo 处理返回的响应，以防在取消请求之前已准备好
     *                     }
     *
     *                     @Override
     *                     public void onFailure(Exception exception) {
     *                          //todo 处理返回的异常，当请求被取消时，很有可能是CancellationException
     *                     }
     *                 }
     *         );
     *         cancellable.cancel();
     */
    public void test004(){

    }
}
