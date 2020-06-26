## docker 安装zookeeper 和dubbo-admin
```shell script
docker run --privileged=true -d --name zookeeper --publish 2181:2181  -d zookeeper:latest
docker run -d -p 8280:8080 --name dubbo-admin -e dubbo.registry.address=zookeeper://192.168.31.240:2181 -e dubbo.admin.root.password=root chenchuxin/dubbo-admin
```



## 使用docker-compose
```dockerfile
version: '3.6.1'

services:
  zoo1:
    image: zookeeper
    restart: always
    hostname: zoo1
    ports:
      - 2181:2181
    environment:
      ZOO_MY_ID: 1
      ZOO_SERVERS: server.1=zoo1:2888:3888
```
然后执行（-d后台启动）
```shell script
docker-compose -f docker-compose.yml up -d
```


## 通过docker-compose制作dubbo-admin和zookeeper组合服务
