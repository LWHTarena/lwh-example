package com.lwhtarena.jpa;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.persistence.SharedCacheMode;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

/**
 * <p>
 * <h2>简述：数据库配置</h2>
 * <ol></ol>
 * <h2>功能描述：</h2>
 * <ol></ol>
 * </p>
 *
 * @PropertySources({
 * 	@PropertySource("classpath:config.properties"),
 * 	@PropertySource("classpath:db.properties")
 * })
 * @Author: liwh
 * @Date :
 * @Version: 版本
 */

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:db.properties")
public class PersistenceJPAConfig {

    @Value("${jdbc.driverClass}")
    String driverClass;

    @Value("${jdbc.url}")
    String url;

    @Value("${jdbc.user}")
    String user;

    @Value("${jdbc.password}")
    String password;

    @Value("${hibernate.dialect}")
    String dialect;

    @Value("${show_sql}")
    String showSQL;

    @Value("${format_sql}")
    String formatSQL;

    @Value("${hbm2ddl.auto}")
    String hbm2ddl;

    @Value("${connection.pool_size}")
    String poolSize;

    @Value("${hibernate.search.default.directory_provider}")
    String searchDirectory;

    @Value("${hibernate.search.default.indexBase}")
    String indexBase;



    /** 配置 JPA 的 EntityManagerFactory**/
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();

        /**配置dataSource 数据源**/
        em.setDataSource(dataSource());

        /**配置扫描的包**/
        em.setPackagesToScan(new String[]{"com.lwhtarena.jpa.entity"});

        /**配置 JPA 提供商的适配器**/
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(jpaProperties());

        /**配置使用二级缓存的模式：只允许带有 @Cacheable 的类使用二级缓存 **/
        em.setSharedCacheMode(SharedCacheMode.ENABLE_SELECTIVE); //配置二级缓存相关

        em.setMappingResources();

        return em;
    }


    @Bean
    public DataSource dataSource(){
        ComboPooledDataSource dataSource =new ComboPooledDataSource();
        try {
            dataSource.setDriverClass("org.h2.Driver");
            dataSource.setJdbcUrl("jdbc:h2:mem:liwh;DB_CLOSE_DELAY=-1;MVCC=TRUE");
            dataSource.setUser("sa");
            dataSource.setPassword("");
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
//        dataSource.setDriverClassName(driverClass);
//        dataSource.setUrl(url);
//        dataSource.setUsername(user);
//        dataSource.setPassword(password);
        return dataSource;
    }

    /** 配置 jpa 的注解
     *  配置 JPA 的注解和基于注解的事务操作
     ***/
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    /**
     * 配置 JPA 实现产品即 Hibernate 的基本属性
     * <prop key="hibernate.ejb.naming_strategy">org.hibernate.cfg.ImprovedNamingStrategy</prop>
     * <prop key="hibernate.hbm2ddl.auto">update</prop>
     * <prop key="hibernate.show_sql">true</prop>
     * <prop key="hibernate.format_sql">true</prop>
     * <prop key="hibernate.dialect">org.hibernate.dialect.MySQL5InnoDBDialect</prop>
     * <prop key="hibernate.cache.use_second_level_cache">true</prop>
     * <prop key="hibernate.cache.region.factory_class">org.hibernate.cache.ehcache.EhCacheRegionFactory</prop>
     * <prop key="hibernate.cache.use_query_cache">true</prop>
     *
     * @return
     */
    Properties jpaProperties(){
        Properties properties = new Properties();
        properties.setProperty("hibernate.ejb.naming_strategy","org.hibernate.cfg.ImprovedNamingStrategy");
        properties.setProperty("hibernate.hbm2ddl.auto","update");
        properties.setProperty("hibernate.show_sql","true");
        properties.setProperty("hibernate.format_sql","true");
        properties.setProperty("hibernate.dialect","org.hibernate.dialect.H2Dialect");
//        properties.setProperty("hibernate.cache.use_second_level_cache","true");
//        properties.setProperty("hibernate.cache.region.factory_class","org.hibernate.cache.ehcache.EhCacheRegionFactory");
//        properties.setProperty("hibernate.cache.use_query_cache","true");

        /** 配置hibernate search **/
        properties.setProperty("hibernate.search.default.directory_provider","filesystem");
        properties.setProperty("hibernate.search.default.indexBase","/var/lucene/indexes");
        return properties;
    }

}
