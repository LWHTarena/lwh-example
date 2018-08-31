# Spring MVC5 整合 hibbernate5

   随着Servlet 3.0规范的发布，可以用几乎没有xml来配置你的Servlet容器。 为此，Servlet规范
   中有`ServletContainerInitializer`。 在这个类中，你可以注册过滤器，监听器，servlet等，就
   像你在web.xml中一样。
   
   
Spring提供了知道如何处理WebApplicationInitializer类的SpringServletContainerInitializer。
AbstractAnnotationConfigDispatcherServletInitializer类实现了内部实现WebApplicationInitializer的
WebMvcConfigurer。 它注册一个ContextLoaderlistener（可选）和DispatcherServlet，并允许您轻松添加配置
类来加载这两个类，并将过滤器应用于DispatcherServlet并提供servlet映射。

## Tomcat 要求为8

## Spring Web MVC 5 配置

1、WebMvcConfigurer 定义了通过使用@EnableWebMvc自定义或添加到默认的@EnableWebMvc配置的选项。
2、@EnableWebMvc 启用默认的Spring MVC配置，并注册DispatcherServlet所期望的Spring MVC基础架构组件。
3、@Configuration 指示一个类声明了一个或多个@Bean方法，并且可以被Spring容器处理，以在运行时为这些bean生
成bean定义和服务请求。
4、@ComponentScan 注释用于指定要扫描的基本包。任何用@Component和@Configuration注解的类都将被扫描。
5、InternalResourceViewResolver 有助于映射逻辑视图名称，以便在特定的预配置目录下直接查看文件。
6、ResourceBundleMessageSource 使用指定的基本名称访问资源包（这里是消息）。
7、LocalValidatorFactoryBean 引导一个javax.validation.ValidationFactory ，并通过Spring Validator接
口以及JSR-303 Validator接口和ValidatorFactory 接口本身公开它。

## Hibernate 配置

1、LocalSessionFactoryBean 创建一个Hibernate SessionFactory. 这是在Spring应用程序上下文中设置共
享Hibernate SessionFactory的常用方法。
2、EnableTransactionManagement 支持Spring的注解驱动事务管理功能。
3、HibernateTransactionManager 将Hibernate Session从指定的工厂绑定到线程，可能允许每个工厂有一个
线程绑定的Session。 此事务管理器适用于使用单个Hibernate
4、SessionFactory 进行事务性数据访问的应用程序，但也支持事务内的直接DataSource 访问，即普通JDBC。


## hsqldb

HSQLDB(HyperSQL DataBase)是一个开放源代码的JAVA数据库，其具有标准的SQL语法和JAVA接口，它可
以自由使用和分发，非常简洁和快速的。

**注：hsqldb的默认用户是sa密码为空。**

hsqldb 的 Memry-Only数据库

Memory-Only数据库不是持久化的而是全部在随机访问的内存中。因为没有任何信息写在磁盘上。这种模式通过mem:协议的方式来指定：
```$xslt
 Connection c = DriverManager.getConnection("jdbc:hsqldb:mem:dbName", "sa", "");
```
       
你也可以在server.properties中指定相同的URL来运行一个Memory-Only（仅处于内存中）服务器实例。

注意事项：当一个服务器实例启动或者建立一个in-process数据库连接的时候，如果指定的路径没有数据库存在，那么就会创建一个新的
空的数据库。这个特点的副作用就是让那些新用户产生疑惑。在指定连接已存在的数据库路径的时候，如果出现了什么错误的话，就会建立
一个指向新数据库的连接。为了解决这个问题，你可以指定一个连接属性ifexists=true只允许和已存在的数据库建立连接而避免创建新
的数据库，如果数据库不存在的话，getConnection()方法将会抛出异常。