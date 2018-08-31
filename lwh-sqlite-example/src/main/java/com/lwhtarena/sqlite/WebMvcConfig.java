package com.lwhtarena.sqlite;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * <p>
 * <h2>简述：Spring Web MVC 5 配置</h2>
 * <ol></ol>
 * <h2>功能描述：Spring Web MVC 5 配置</h2>
 * <ol></ol>
 * </p>
 *
 * 1、WebMvcConfigurer 定义了通过使用@EnableWebMvc自定义或添加到默认的@EnableWebMvc配置的选项。
 * 2、@EnableWebMvc 启用默认的Spring MVC配置，并注册DispatcherServlet所期望的Spring MVC基础架构组件。
 * 3、@Configuration 指示一个类声明了一个或多个@Bean方法，并且可以被Spring容器处理，以在运行时为这些bean生成bean定义和服务请求。
 * 4、@ComponentScan 注释用于指定要扫描的基本包。任何用@Component和@Configuration注解的类都将被扫描。
 * 5、InternalResourceViewResolver 有助于映射逻辑视图名称，以便在特定的预配置目录下直接查看文件。
 * 6、ResourceBundleMessageSource 使用指定的基本名称访问资源包（这里是消息）。
 * 7、LocalValidatorFactoryBean 引导一个javax.validation.ValidationFactory ，并通过Spring Validator接
 * 口以及JSR-303 Validator接口和ValidatorFactory 接口本身公开它。
 *
 * @Author: liwh
 * @Date :
 * @Version: 版本
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.lwhtarena.sqlite"})
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public InternalResourceViewResolver resolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasename("messages");
        return source;
    }

    @Override
    public Validator getValidator() {
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.setValidationMessageSource(messageSource());
        return validator;
    }

}
