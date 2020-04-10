# spring-boot-starter-parent 与 spring-boot-dependencies

Spring Boot的每个发布版本都会规划它所支持的依赖项。实际上，你不用指定这些依赖项的版本号，因为Spring Boot都为
你管理好了。当更新Spring Boot时，会相应的更新依赖。

## Maven管理依赖
Maven用户可以继承spring-boot-starter-parent项目，来获取最佳依赖。这个父项目提供了以下几个功能：   
   - 默认Java 1.6编译
   - UTF-8编码格式
   - 依赖管理部分，可让你对公共依赖省略version标签。继承自spring-boot-dependencies POM。
   - 良好的资源过滤
   - 良好的插件配置
   - 对于application.properties和application.yml包括profile-specific文件，良好的资源过滤
最后一点：因为默认配置文件接受Spring风格的占位符(${})，Maven过滤器换成了@...@占位符。（可以通过Maven属性resource.delimiter替换）

## 继承starter parent
配置继承spring-boot-starter-parent:
```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.1.3.RELEASE</version>
    <relativePath/> <!-- lookup parent from repository -->
</parent>
```
只需要在这里指定Spring Boot的版本号。如果导入其他的starters，你可以完全省略版本号。

使用这个配置，你还可以通过property覆盖内部的依赖。例如，在pom.xml中升级Spring Data release train。
```xml
<properties>
     <spring-data-releasetrain.version>Fowler-SR2</spring-data-releasetrain.version>
</properties>
```
## 不使用parent POM，配置Spring Boot
可能有人不喜欢继承spring-boot-starter-parent POM。你可能有自己的企业标准parent，或者你可能只是比较喜欢明确声明所有的Maven配置。
如果你不想使用spring-boot-starter-parent，你依然可以通过使用scope=import利用依赖管理的便利：
```xml
<dependencyManagement>
     <dependencies>
        <dependency>
            <!-- Import dependency management from Spring Boot -->
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-dependencies</artifactId>
            <version>2.1.3.RELEASE</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```
这种方式不能使用property的形式覆盖原始的依赖项。要达到同样的效果，需要在dependencyManagement里面
的spring-boot-dependencies之前添加依赖的东西。例如，要升级Spring Data release train，pom.xml应该是这样的：
```xml
<dependencyManagement>
    <dependencies>
        <!-- Override Spring Data release train provided by Spring Boot -->
        <dependency>
            <groupId>org.springframework.data</groupId>
            <artifactId>spring-data-releasetrain</artifactId>
            <version>Fowler-SR2</version>
            <scope>import</scope>
            <type>pom</type>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-dependencies</artifactId>
            <version>2.1.3.RELEASE</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```
