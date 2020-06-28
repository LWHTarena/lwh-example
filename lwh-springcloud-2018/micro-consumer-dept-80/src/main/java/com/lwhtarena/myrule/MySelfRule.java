package com.lwhtarena.myrule;

import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liwh
 * @Title: MySelfRule
 * @Package com.lwhtarena.myrule
 * @Description:
 * @Version 1.0.0
 * @date 2020/6/28 11:45
 */
@Configuration
public class MySelfRule {

    @Bean
    public IRule myRule(){
        /**ribbon默认是轮询，我自定义为随机**/
//        return new RandomRule();
//        return new RoundRobinRule();
        return new RandomRule_ZY();
    }
}
