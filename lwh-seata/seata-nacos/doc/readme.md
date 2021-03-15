# springcloud-nacos-seata 实现分布式事务

1、修改TC服务的配置,详见register.conf、file.conf

2、创建seata数据库，并创建三张表，详见seata.sql

3、将seata配置推送到nacos
ps:这里因为是mysql8.0.因此这里用的是com.mysql.cj.jdbc.Driver，否则在启动seata时，会报空指针，获取连接失败。

注：store.db.driverClassName这个配置如果是mysql8.0，这里需要改成com.mysql.cj.jdbc.Driver，刚开始我使用默认
的com.mysql.jdbc.Driver，导致seata无法启动，连接不上。 使用脚本，将seata的配置推送到nacos中：

```shell
[root@localhost conf]# sh nacos-config.sh -h 192.168.56.15 -g 'SEATA_GROUP' -t '0efe6c3c-c865-4b16-9de8-5c2605d8f6b6'
```
注：如果这一步推送服务出错，而你确实是从seata的GitHub中拷贝下来的，确保没有问题，却提示语法错误，首先看下文件是否是utf-8。
```properties
参数说明：
-h: host，默认值 localhost
-p: port，默认值 8848
-g: 配置分组，默认值为 'SEATA_GROUP'
-t: 租户信息，对应 Nacos 的命名空间ID字段, 默认值为空 ''
```
执行脚本后，控制台打印

4、启动seata服务

```shell
[root@localhost seata]# cd bin
[root@localhost bin]# sh seata-server.sh -p 8091 -h 192.168.24.140 -m db
#下面两种启动命令参考：
cd bin
sh seata-server.sh -p 8091 -m db
#or 
sh seata-server.sh
```
注：如果seata服务端的registy.conf文件没有配置，启动seata会提示无法创建mysql连接，并一直空指针异常，假如你想使用nacos做为配
置中心，里面存放seata的配置，那么就是从nacos中读取你的配置，你不配置registy.conf，seata就读不到。

5、所有的微服务registry.conf中内容挪到了application.yml中
application.yml: （registry.conf中内容挪到了application.yml中，因此不需要registry.conf文件，只要一个application.yml即可）