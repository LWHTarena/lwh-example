
## Spring Data框架

Spring Data项目是为了简化构建基于Spring框架应用的数据访问技术，是Spring官方提供的一套数据层的综合解决方案。（或者可以封装其他的持久层解决方案的一个解决方案）。它支持关系型数据库、非关系型数据库、Map-Reduce框架、云数据服务等。 

**Spring Data JPA是Spring Data框架的一个模块。**
 
**Spring Data JPA**依赖于Spring的核心jar,JPA只有接口和注解，Spring Data JPA的功能实现默是使用的Hibernate，因此还必须引入Hibernate对JPA的支持（整合）项目hibernate-entitymanager。


### Spring Data JPA的Repository 开发

Spring Data JPA整合jpa（写dao接口+配置扫描—约定机制-它的整合相当于零配置）。 
Spring Data JPA的编程方式和以前不同。无需写实现类，只需要写接口。 
它的DAO(Repository)已经写好了默认的实现类，对于大部分通用功能，我们只需要提供接口即可使用。 
使用方法：我们构建一个接口，继承如下的任意接口即可使用其内部提供的功能。

**Dao接口编写规则：**
```$xslt
- Repository (空接口) 
- CrudRepository (增删改查) 
- PagingAndSortingRepository (分页和排序) 
- JpaRepository (扩展增删改查、批量操作) 
- JpaSpecificationExecutor (用来负责查询的接口) 
- Specification：是Spring Data JPA 提供的一个查询规范，做复杂的查询。 
```


### 框架整合三种方式

第一种方式，整个Dao层全部使用Repository接口，具体实现由Spring Data Jpa中的SimpleJpaRepository类来实现。用户在编写Dao层时，只需要提供接口即可。

第二种方式，由于第一种方式中编写的Repository接口全部有Spring Data Jpa中的SimpleJpaRepository类来实现，例如要对UserInfoRepository接口自定义一些其他的方法。

第三种方法，第二种方式只是对某一个接口自定义一些其他的方法，第三种方式在为整个Repository接口自定义方法，在用户编写每一个接口时，只需要继承该自定义的接口即可，由spring data jpa和用户共同实现。
