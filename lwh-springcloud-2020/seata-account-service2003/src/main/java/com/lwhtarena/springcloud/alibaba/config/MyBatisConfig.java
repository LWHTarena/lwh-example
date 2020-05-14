package com.lwhtarena.springcloud.alibaba.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author liwh
 * @Title: MyBatisConfig
 * @Package com.lwhtarena.springcloud.alibaba.config
 * @Description:
 * @Version 1.0.0
 * @date 2020/5/14 13:02
 */
@Configuration
@MapperScan({"com.lwhtarena.springcloud.alibaba.dao"})
public class MyBatisConfig {
}
