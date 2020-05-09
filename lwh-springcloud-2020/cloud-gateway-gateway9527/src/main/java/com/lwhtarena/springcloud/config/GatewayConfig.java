package com.lwhtarena.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liwh
 * @Title: GatewayConfig
 * @Package com.lwhtarena.springcloud.config
 * @Description: 配置路由（注解方式）
 * @Version 1.0.0
 * @date 2020/5/9 20:16
 */
@Configuration
public class GatewayConfig {

    /**
     * 配置了一个id为route-name的路由规则，当访问地址 http://localhost:9527/guonei 时会自动转发到地址 http://news.baidu.com/guonei
     * @param routeLocatorBuilder
     * @return
     */
    @Bean
    public RouteLocator customeRouteLocator(RouteLocatorBuilder routeLocatorBuilder){
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        routes.route("lwh_route",r -> r.path("/guonei").uri("http://news.baidu.com/guonei")).build();
        return routes.build();
    }
}
