# 使用框架

##  jedis 接近原生命令


##  lettuce-core 的执行框架
lettuce 提供了`Synchronous`、`Asynchronous` 和 `Reactive` 三种 API，可谓是相当的现代化。

在使用方式上都是先建立连接：
```java
RedisClient client = RedisClient.create("redis://localhost");
StatefulRedisConnection<String, String> connection = client.connect();

//从 StatefulRedisConnection 获取各种 API 接口封装：
// 同步
RedisStringCommands sync = connection.sync();
// 异步
RedisStringAsyncCommands<String, String> async = connection.async();
//  响应式
RedisStringReactiveCommands<String, String> reactive = connection.reactive();
```