# rabbitmq 环境

```shell script
docker run -d --hostname rabbitbus --name rabbitmqbus -p 15672:15672 -p 5672:5672 rabbitmq:latest
```
 -d：后台启动

 --hostname：指定主机名

 --name：指定容器名

 -p：端口映射（主机端口：容器端口）

 -e：指定容器运行时传入rabbitmq参数，其中 RABBITMQ_ERLANG_COOKIE 为rabbitmq多节点之间通信所用到的cookie，rabbitmq集群就是利用这一特性实现的

    最后的一串hash码 是rabbitmq镜像名

访问：`http://server-ip:15672` 即可。

**docker安装RabbitMQ后无法访问web界面问题**

我们将rabbitmq_management这个插件启动，然后就好了

`docker exec -it rabbit rabbitmq-plugins enable rabbitmq_management`