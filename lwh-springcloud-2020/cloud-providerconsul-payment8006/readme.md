# consule

## docker 使用consul
```shell script
docker pull consul
```

## 启动镜像
```shell script
## 单机版
docker run -d --name consulserver1 --net=host -e'CONSUL_LOCAL_CONFIG={"skip_leave_on_interrupt": true}' consul agent -server -bind=10.1.54.162 -bootstrap-expect=1  -client 0.0.0.0 -ui
```
–net=host docker参数, 使得docker容器越过了netnamespace的隔离，免去手动指定端口映射的步骤

-server consul 支持以server或client的模式运行, server是服务发现模块的核心, client主要用于转发请求

-advertise 将本机私有IP传递到consul

-bootstrap-expect 指定consul将等待几个节点连通，成为一个完整的集群

-retry-join 指定要加入的consul节点地址，失败会重试, 可多次指定不同的地址

-client consul绑定在哪个client地址上，这个地址提供HTTP、DNS、RPC等服务，默认是127.0.0.1

-bind 该地址用来在集群内部的通讯，集群内的所有节点到地址都必须是可达的，默认是0.0.0.0

-allow_stale 设置为true, 表明可以从consul集群的任一server节点获取dns信息, false则表明每次请求都会经过consul server leader

--name DOCKER容器的名称

-client 0.0.0.0 表示任何地址可以访问。

-ui  提供图形化的界面。

## 启动第一个节点
```shell script
docker run --name consul1 -d -p 8500:8500 -p 8300:8300 -p 8301:8301 -p 8302:8302 -p 8600:8600 consul agent -server -bootstrap-expect 2 -ui -bind=0.0.0.0 -client=0.0.0.0

##单机版 docker run --name consul1 -d -p 8500:8500 -p 8300:8300 -p 8301:8301 -p 8302:8302 -p 8600:8600 consul agent -server -bootstrap-expect=1 -ui -bind=0.0.0.0 -client=0.0.0.0

查看consul1的ip地址
docker inspect --format='{{.NetworkSettings.IPAddress}}' consul1

```
端口详解

- 8500 : http 端口，用于 http 接口和 web ui访问；
- 8300 : server rpc 端口，同一数据中心 consul server 之间通过该端口通信；
- 8301 : serf lan 端口，同一数据中心 consul client 通过该端口通信; 用于处理当前datacenter中LAN的gossip通信；
- 8302 : serf wan 端口，不同数据中心 consul server 通过该端口通信; agent Server使用，处理与其他datacenter的gossip通信；
- 8600 : dns 端口，用于已注册的服务发现；

## 启动第二个节点，加入到 consul1
```shell script
##开启第二个节点（端口8501），并加入到 consul1
docker run --name consul2 -d -p 8501:8500 consul agent -server -ui -bind=0.0.0.0 -client=0.0.0.0 -join 172.17.0.4

#开启第三个节点（端口8502），并加入到consul1’
docker run --name consul3 -d -p 8502:8500 consul agent -server -ui -bind=0.0.0.0 -client=0.0.0.0 -join 172.17.0.4

查看consul集群信息
docker exec -it consul1 consul members

```
##查看集群信息
我们可以打开浏览器： http://localhost:8500 来查看整个集群的信息