package com.lwhtarena.spring.config;

import com.lwhtarena.spring.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author liwh
 * @Title: MainConfig
 * @Package com.lwhtarena.spring.config
 * @Description: 配置类 == 配置文件（xml文件）
 *
 * <pre>
 *  @ComponentScan  value:指定要扫描的包
 *      excludeFilters = Filter[] ：指定扫描的时候按照什么规则排除那些组件
 *      includeFilters = Filter[] ：指定扫描的时候只需要包含哪些组件
 *          FilterType.ANNOTATION：按照注解
 *          FilterType.ASSIGNABLE_TYPE：按照给定的类型；
 *          FilterType.ASPECTJ：使用ASPECTJ表达式
 *          FilterType.REGEX：使用正则指定
 *          FilterType.CUSTOM：使用自定义规则
 * </pre>
 *
 * @Version 1.0.0
 * @date 2020/5/22 08:58
 */
@Configuration //配置类 == 配置文件;告诉Spring IOC容器，这是一个配置类
@ComponentScan(value = "com.lwhtarena.spring") //指定要扫描的bao
//@ComponentScan(value = "com.lwhtarena.spring",excludeFilters = {
//        @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Controller.class, Service.class}) //排除controller、service注解
//},includeFilters = {
////        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,classes = {Bookservice.class})，
//        @ComponentScan.Filter(type = FilterType.CUSTOM,classes = {MyTypeFilter.class}) //自定义扫描
//}
//)//excludeFilters、includeFilters 雷同
public class MainConfig {

    /**
     * 等价于
     * <bean id="persion" class="com.lwhtarena.spring.bean.Persion">
     *     <property name="name" value="周润发"></property>
     *     <property name="age" value="70"></property>
     * </bean>
     */
    @Bean //给容器中注册一个Bean；类型为返回值的类型，id默认是用方法名作为id
    public Person persion(){
        return new Person("周润发",70);
    }

//    @Bean(value = "persion3") //给容器中注册一个Bean；类型为返回值的类型，id默认是用方法名作为id
//    public Persion persion2(){
//        return new Persion("周润发",55);
//    }

}
