package com.lwhtarena.spring.config;

import com.lwhtarena.spring.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author liwh
 * @Title: MainConfigOfPropertyValues
 * @Package com.lwhtarena.spring.config
 * @Description: 使用@PropertySource读取外部配置文件中的k/v保存到运行的环境变量中;加载完外部的配置文件以后使用${}取出配置文件的值
 * @Version 1.0.0
 * @date 2020/5/22 16:45
 */
@PropertySource(value={"classpath:/person.properties"})
@Configuration
public class MainConfigOfPropertyValues {
    @Bean
    public Person person(){
        return new Person();
    }
}
