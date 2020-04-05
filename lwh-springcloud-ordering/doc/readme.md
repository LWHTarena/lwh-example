# 微服务

包括了诸如下列功能：
- Eureka服务注册发现
- 统一配置中心
- Spring Cloud Stream异步调用
- Zuul服务网关
- Hystrix服务降级容错
- 服务调用跟踪

## 微服务的优缺点

**==优点:==**
- 每个服务足够内聚，足够小（一般是同一类型的业务归为一个服务），代码容易理解；
- 是松耦合的

**==缺点:==**
- 复杂度大大增加
- 运维成本增加
- 系统部署依赖
- 增加服务之间的通信成本
- 数据一致性更难保证

## Spring Cloud和 Spring Boot 区别，什么关系
- 存在依赖关系（boot可以离开cloud 独立使用开发项目，但是cloud离不开boot）
- Spring boot专注于快速方便的开发单个个体微服务。
- Spring Cloud是关注全局的微服务协调整理治理框架，它将Spring Boot开发的一个个单体微服务整合并且管理起来，为各个微服务之间提供一整套的服务。

## spring cloud与dubbo的区别
   
- 服务之间 spring cloud 是采用轻量级的通信机制互相沟通（通常是基于HTTP的RESTful API）
- dubbo 是采用 RPC


## 结构模块划分
- server 所有业务逻辑
- client 对外暴露的接口
- common 共用对象

## 微服务、Docker 和 DevOps