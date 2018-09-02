package com.lwhtarena.quartz.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classPath:config.properties")
@ComponentScan(basePackages = {"com.lwhtarena.quartz.service","com.lwhtarena.quartz.dao"})
public class SpringConfig {

    @Autowired
    private Environment env;

//    @Bean(name = "mysqlDataSource")
//    public DataSource mysqlDataSource() {
//        ProxoolDataSource dataSource = new ProxoolDataSource();
//        dataSource.setDriver(env.getProperty("ds.driver.classname"));
//        dataSource.setDriverUrl(env.getProperty("ds.url"));
//        dataSource.setUser(env.getProperty("ds.username"));
//        dataSource.setPassword(env.getProperty("ds.password"));
//        dataSource.setPrototypeCount(env.getProperty("proxool.prototype", Integer.class));
//        dataSource.setMinimumConnectionCount(env.getProperty("proxool.minimum", Integer.class));
//        dataSource.setMaximumConnectionCount(env.getProperty("proxool.maximum", Integer.class));
//        dataSource.setSimultaneousBuildThrottle(env.getProperty("proxool.simultaneous", Integer.class));
//        dataSource.setTestBeforeUse(true);
//        dataSource.setHouseKeepingTestSql(env.getProperty("proxool.testSql"));
//        return dataSource;
//    }
//
//
//    @Bean(name = "exampleBean")
//    public ExampleBean getExampleBean(){
//        return new ExampleBean();
//    }
}
