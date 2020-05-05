# docker 拉取zookeeper

## 获取镜像
```shell script
docker pull zookeeper
```

## 启动容器并添加映射
```shell script
##privilegeed 给足权限，想干嘛干嘛
##只开放一个2181就可以了
docker run --privileged=true -d --name zookeeper --publish 2181:2181  -d zookeeper:latest
```
## 查看容器是否启动
```shell script
docker ps
```

##idea提供了一个Zookeeper插件，以供连接Zookeeper服务中心和查看内容
  
打开idea –》 Settings -》Plugins,搜索Zoo进行下载安装

## docker 下的zookeeper常用命令

常用命令
```shell script
1.首先使用命令进入服务器: docker exec -it 容器id /bin/bash

2.使用命令 ./bin/zkServer.sh status 来查看节点的状态


3.使用zkCli.sh开启客户端


```