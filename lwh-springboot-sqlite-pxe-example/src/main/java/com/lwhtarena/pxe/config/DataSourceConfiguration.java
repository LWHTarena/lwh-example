package com.lwhtarena.pxe.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.sqlite.SQLiteDataSource;

import javax.sql.DataSource;

/**
 * <p>
 * <h2>简述：配置数据源</h2>
 * <ol></ol>
 * <h2>功能描述：配置数据源</h2>
 * <ol></ol>
 * </p>
 *
 * @Author: liwh
 * @Date :
 * @Version: 版本
 */

@Configuration
public class DataSourceConfiguration {

    @Bean(destroyMethod = "",name = "EmbeddeddataSource")
    public DataSource dataSource(){
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.sqlite.JDBC");
        dataSourceBuilder.url("jdbc:sqlite:" + "winhong-pxe.db");
        dataSourceBuilder.username("winhong");
        dataSourceBuilder.password("winhong!@#");
        dataSourceBuilder.type(SQLiteDataSource.class);
        return dataSourceBuilder.build();
    }
}
