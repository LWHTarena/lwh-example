package com.lwhtarena.web.config;

import com.lwhtarena.web.Filter.TimeFilter;
import com.lwhtarena.web.interceptor.TimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liwh
 * @Title: WebConfig
 * @Package com.lwhtarena.web.config
 * @Description:
 * @Version 1.0.0
 * @date 2020/7/20 22:35
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @SuppressWarnings("unused")
    @Autowired
    private TimeInterceptor timeInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(timeInterceptor);
    }

    //	@Bean
    public FilterRegistrationBean timeFilter() {

        FilterRegistrationBean registrationBean = new FilterRegistrationBean();

        TimeFilter timeFilter = new TimeFilter();
        registrationBean.setFilter(timeFilter);

        List<String> urls = new ArrayList<>();
        urls.add("/*");
        registrationBean.setUrlPatterns(urls);

        return registrationBean;

    }

}
