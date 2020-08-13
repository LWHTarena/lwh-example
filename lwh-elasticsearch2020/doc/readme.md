# elasticsearch 使用

## 低级的client  

客户通过客户端http来连接Elasticsearch集群（兼容性好）

1、依赖
```xml
<dependency>
    <groupId>org.elasticsearch.client</groupId>
    <artifactId>elasticsearch-rest-client</artifactId>
    <version>7.6.2</version>
</dependency>
```

## 高级的client

1、依赖
```xml
<dependency>
    <groupId>org.elasticsearch.client</groupId>
    <artifactId>elasticsearch-rest-high-level-client</artifactId>
    <version>7.6.2</version>
</dependency>
```

## Spring Data ElasticSearch 
Spring Data项目对ElasticSearch做了支持，其目的就是简化对ElasticSearch的操作
```xml
<dependency>
    <groupId>org.springframework.data</groupId>
    <artifactId>spring-data-elasticsearch</artifactId>
    <version>3.2.6.RELEASE</version>
</dependency>
```