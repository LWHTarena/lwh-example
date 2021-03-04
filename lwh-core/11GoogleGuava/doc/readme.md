# 常用Java 工具


## 1、通用类库

有几个很好的第三方通用库可供Java开发人员使用，例如 Apache Commons 和 Google Guava 。我会经常在我
的代码中使用这些通用类库，因为这些类库都是经过无数开发者实践过的，无论是实用性还是在性能等方面都是最佳的


### 国内开源工具类Hutool和Google的Guava的比较

Guava包含了若干被Google的 Java项目广泛依赖 的核心库，例如：集合 [collections] 、缓存 [caching] 、
原生类型支持 [primitives support] 、并发库 [concurrency libraries] 、通用注解 [common annotations] 、
字符串处理 [string processing] 、I/O 等等。 所有这些工具每天都在被Google的工程师应用在产品服务中。

Hutool是一个Java工具集，针对项目中util包进行开刀，抽象大量的工具方法，旨在减少项目中工具类的数量，
将我们的编码工作专注在业务上。Hutool 对文件、流、加密解密、转码、正则、线程、XML、日期、Http客户端 
等 JDK 方法进行封装，组成各种 Util 工具类。 Hutool并非框架，而是一个工具类方法集合，随用随取，且随时
可被替换，与任何框架不冲突。 Hutool的大部分工具方法并不依赖第三方包（extra模块对第三方框架封装工具类除
外），轻量简洁。


## 2、commons-configuration 配置管理类库

## 3、日志类库

ava开发人员应该熟悉日志记录的利弊， 并且了解为什么SLF4J要比Log4J要好。
 
### 3.1 Log4j 
### 3.2 SLF4j 
### 3.3 LogBack。

## 4、JSON解析库

一个Java web开发人员应该熟悉Jackson、Gson 和 fastjson这两种中的至少一种库。

### 4.1 Jackson
### 4.2 Gson
### 4.3 fastjson

## 5、单元测试库

单元测试技术的使用，是区分一个一般的开发者和好的开发者的重要指标。程序员经常有各种借
口不写单元测试，但最常见的借口就是缺乏经验和知识。常见的单测框架有 JUnit , Mockito 
和PowerMock 。

### 5.1 JUnit
### 5.2 Mockito
### 5.3 PowerMock

## 6、XML解析库

市面上有很多XML解析的类库，如 Xerces , JAXB , JAXP , Dom4j , Xstream 等。 Xerces2是
下一代高性能，完全兼容的XML解析工具。Xerces2定义了 Xerces Native Interface (XNI)规范，
并提供了一个完整、兼容标准的 XNI 规范实现。该解析器是完全重新设计和实现的，更简单以及模块化。

## 7、Excel读写库

许多应用程序需要提供把数据导出到Excel的功能，如果你要做相同的Java应用程序,那么你需要 Apache POI API 。
这是一个非常丰富的类库，你可以从Java程序读写XLS文件。

### 7.1 Apache POI
### 7.2 EasyPoi

## 8、字节码库

如果你正在编写一个框架或者类库。有一些受欢迎的字节码库如 `javassist` 和 `Cglib Nodep` 可以供
你选择，他们可以让你阅读和修改应用程序生成的字节码。


Javassist使得JAVA字节码操作非常简单。它是一个为编辑Java字节码而生的类库。 ASM 是另一个有用的字节码编辑库。

## 9、消息传递库

像日志和数据库连接池一样，消息传递也是很多实际的Java项目中必备的。Java提供了JMS Java消息服务，但
这不是JDK的一部分,你需要单独的引入jms.jar。类似地，如果您准备使用第三方消息传递协议， `Tibco RV` 是个不错的选择。

## 10、PDF处理库

除了Excel和Word，PDF也是一种常用的文件格式。如果你的应用程序要支持PDF格式的文件处理，你可以使
用 `iText` 和 `Apache FOP` 类库。两者都提供了非常有用的PDF处理功能。

### 10.1 iText
### 10.2 Apache FOP

## 11、日期和时间库

在Java之前，JDK的日期和时间库一直被人们所诟病，比如其非线程安全的、不可变的、容易出错等。很多开
发人员会选择更好用的 JodaTime 类库。


但是在Java8推出之后，我们就可以彻底放弃JodaTime了，因为Java 8提供了其所有功能。但是，如果你的
代码运行在一个低版本的JDK中，那么JodaTime还是值得使用的。

### 11.1 JodaTime

### 11.2 java8 内置

## 12、集合类库

虽然JDK有丰富的集合类，但还是有很多第三方类库可以提供更多更好的功能。如 `Apache Commons Collections` 、 
`Goldman Sachs collections` 、 `Google Collections` 和 `Trove` 。Trove尤其有用，因为它提供所有标准
Collections 类的更快的版本以及能够直接在原语（primitive）（例如包含int 键或值的Map 等）上操作的
Collections 类的功能。


`FastUtil`是另一个类似的API，它继承了Java Collection Framework，提供了数种特定类型的容器，包括映射map、
集合set、列表list、优先级队列（prority queue），实现了java.util包的标准接口（还提供了标准类所没有的双
向迭代器），还提供了很大的（64位）的array、set、list，以及快速、实用的二进制或文本文件的I/O操作类。

## 13、邮件API

javax.mail 和 Apache Commons Email 提供了发送邮件的api。它们建立在JavaMail API的基础上，提供简化的用法。

### 13.1 javax.mail 
### 13.1 Apache Commons Email

## 14 HTML解析库

和XML与JSON类似，HTML是另外一种我们可能要打交道的传输格式。值得庆幸的是，我们有jsoup可以大大简化Java应用程
序使用HTML。你不仅可以使用 JSoup 解析HTML还可以创建HTML文档。 

### 14.1 JSoup

## 15、加密库（Commons Codec）

Apache Commons家族中的 Commons Codec 就提供了一些公共的编解码实现，比如Base64, Hex, MD5,Phonetic and URLs等等。

## 16、嵌入式SQL数据库库

我真的是非常喜欢像 H2 这种内存数据库，他可以嵌入到你的Java应用中。在你跑单测的时候如果你需要一个数据库，用来验证你的
SQL的话，他是个很好的选择。顺便说一句,H2不是唯一嵌入式DB，你还有 Apache Derby 和 HSQL 可供选择。

### 16.1 H2
### 16.2 Apache Derby
### 16.3 HSQL
### 16.4 Neo4j  --在Java中实现的开源图形数据库。

Neo4j是一个高性能的NOSQL图形数据库，它将结构化数据存储在网络上而不是表中。它是一个嵌入式的、基于磁盘的、具备完全事务特性的Java持久化引擎。

## 17、JDBC故障诊断库

有不错的JDBC扩展库的存在使得调试变得很容易，例如P6spy，这是一个针对数据库访问操作的动态监测框架，它使得数据库
数据可无缝截取和操纵，而不必对现有应用程序的代码作任何修改。 P6Spy 分发包包括P6Log，它是一个可记录任何 Java
 应用程序的所有JDBC事务的应用程序。其配置完成使用时，可以进行数据访问性能的监测。
 
## 18、序列化库

Google Protocol Buffer是一种轻便高效的结构化数据存储格式，可以用于结构化数据串行化，或者说序列化。它很适合做
数据存储或 RPC 数据交换格式。可用于通讯协议、数据存储等领域的语言无关、平台无关、可扩展的序列化结构数据格式。
目前提供了 C++、Java、Python 三种语言的 API。

## 19、网络库

一些有用的网络库主要有 Netty 的和 Apache MINA 。如果您正在编写一个应用程序，你需要做的底层网络任务，可以考虑使用这些库。

### 19.1 Netty
### 19.2 Apache MINA