package com.lwhtarena.web;

import org.hsqldb.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * <p>
 * <h2>简述：Hibernate 配置</h2>
 * <ol></ol>
 * <h2>功能描述：.Hibernate 配置</h2>
 * <ol></ol>
 * </p>
 *
 * 1、LocalSessionFactoryBean 创建一个Hibernate SessionFactory. 这是在Spring应用程序上下文中设置共
 * 享Hibernate SessionFactory的常用方法。
 *
 * 2、EnableTransactionManagement 支持Spring的注解驱动事务管理功能。
 *
 * 3、HibernateTransactionManager 将Hibernate Session从指定的工厂绑定到线程，可能允许每个工厂有一个
 * 线程绑定的Session。 此事务管理器适用于使用单个Hibernate
 *
 * 4、SessionFactory 进行事务性数据访问的应用程序，但也支持事务内的直接DataSource 访问，即普通JDBC。
 * @Author: liwh
 * @Date :
 * @Version: 版本
 */

@Configuration
@EnableTransactionManagement
public class HibernateConfig {

    @Autowired
    private ApplicationContext context;

    @Bean
    public LocalSessionFactoryBean getSessionFactory() {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setConfigLocation(context.getResource("classpath:hibernate.cfg.xml"));
        factoryBean.setAnnotatedClasses(User.class);
        return factoryBean;
    }

    @Bean
    public HibernateTransactionManager getTransactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(getSessionFactory().getObject());
        return transactionManager;
    }
}
