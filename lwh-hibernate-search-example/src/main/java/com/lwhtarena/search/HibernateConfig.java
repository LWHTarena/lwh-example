package com.lwhtarena.search;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.SharedCacheMode;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;

/**
 * <p>
 * <h2>简述：</h2>
 * <ol></ol>
 * <h2>功能描述：</h2>
 * <ol></ol>
 * </p>
 *
 * @Author: liwh
 * @Date :
 * @Version: 版本
 */
@Configuration
@PropertySource("jdbc.properties")
public class HibernateConfig {

    @Value("${jdbc.driverClass}")
    String driverClass;

    @Value("${jdbc.url}")
    String url;

    @Value("${jdbc.user}")
    String user;

    @Value("${jdbc.password}")
    String password;

    @Bean
    public DataSource dataSource() throws  PropertyVetoException{
        ComboPooledDataSource dataSource =new ComboPooledDataSource();
        dataSource.setDriverClass(driverClass);
        dataSource.setJdbcUrl(url);
        dataSource.setUser(user);
        dataSource.setPassword(password);

        return dataSource;
    }

//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws PropertyVetoException {
//        Map<String, Object> properties = new Hashtable<String,Object>();
//        properties.put("javax.persistence.schema-generation.database.action","none");
//        /* 允许Hibernate ORM使用Hibernate Search。采用Lucene standalone (no Solr)，并将索引保存在本地文件系统。
//         * 当本war里面的Hibernate ORM相关的数据库写时，将触发Hibernate search对相关内容进行索引，写入到文件中。
//         * 这种方式不适用于Tomcat集群的方式，如果采用Tomcat集群，需要使用Solr server。*/
//        properties.put("hibernate.search.default.directory_provider", "filesystem");
//        /* 本例放在 ../searchIndexes，开发环境中为eclipse的第一级目录 */
//        properties.put("hibernate.search.default.indexBase", "../searchIndexes");
//        properties.put("hibernate.show_sql", "true");
//        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
//
//        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
//        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//        factory.setDataSource(this.springJpaDataSource());
//        factory.setPackagesToScan("cn.wei.flowingflying.chapter23.entities");
//        factory.setSharedCacheMode(SharedCacheMode.ENABLE_SELECTIVE);
//        factory.setValidationMode(ValidationMode.NONE);
//        factory.setJpaPropertyMap(properties);
//
//        return factory;
//
//    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() throws PropertyVetoException{
        LocalSessionFactoryBean sessionFactory =new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        Properties hibernateProperties =new Properties();
        hibernateProperties.setProperty("hibernate.dialect","");
        sessionFactory.setHibernateProperties(hibernateProperties);
        sessionFactory.setPackagesToScan("com.lwhtarena.search.*.pojo");
        return sessionFactory;
    }

    @Bean
    public HibernateTransactionManager txManager() throws PropertyVetoException{
        HibernateTransactionManager txManager =new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory().getObject());
        return txManager;
    }


}
