package com.lwhtarena.spring.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author liwh
 * @Title: MacCondition
 * @Package com.lwhtarena.spring.condition
 * @Description:
 * @Version 1.0.0
 * @date 2020/5/22 11:29
 */
public class MacCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata annotatedTypeMetadata) {
        Environment environment = context.getEnvironment();
        String property = environment.getProperty("os.name");
        if(property.contains("Mac OS X")){
            return true;
        }
        return false;
    }
}
