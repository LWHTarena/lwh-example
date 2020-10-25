package com.elastic.job.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * @author liwh
 * @Title: TaskJobSign
 * @Package com.elastic.job.annotation
 * @Description:
 * @Version 1.0.0
 * @date 2020/10/25 23:27
 */

@Inherited
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface TaskJobSign {

    @AliasFor("cron")
    String value() default "";

    @AliasFor("value")
    String cron() default "";

    String jobName() default "";

}
