package com.lwhtarena.springmvc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 * @author liwh
 * @Title: RootConfig
 * @Package com.lwhtarena.springmvc.config
 * @Description:
 * @Version 1.0.0
 * @date 2020/5/23 13:09
 */
//Spring的容器不扫描controller;父容器
@ComponentScan(value="com.lwhtarena.springmvc",excludeFilters={
        @ComponentScan.Filter(type= FilterType.ANNOTATION,classes={Controller.class})
})
public class RootConfig {

}
