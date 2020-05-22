package com.lwhtarena.spring.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author liwh
 * @Title: WindowsCondition
 * @Package com.lwhtarena.spring.condition
 * @Description: 判断是否windows系统
 * @Version 1.0.0
 * @date 2020/5/22 10:59
 */
public class WindowsCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment environment = context.getEnvironment();
        String property = environment.getProperty("os.name");
        if(property.contains("Windows")){
            return true;
        }
        return false;
    }

}
