package com.lwhtarena.jpa;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

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
public class WebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    /**
     * 获取配置信息
     * @return
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { MvcConfig.class, PersistenceJPAConfig.class}; //CacheConfig.class
    }
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { MvcConfig.class,PersistenceJPAConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

    @Override
    protected Filter[] getServletFilters() {
        return null;
    }
}
