package com.lwhtarena.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liwh
 * @Title: MySelfRule
 * @Package com.lwhtarena.myrule
 * @Description: 自定义均衡rule
 * @Version 1.0.0
 * @date 2020/5/6 22:44
 */
@Configuration
public class MySelfRule {

    @Bean
    public IRule myRule(){
        return new RandomRule();//定义为随机
    }

}
