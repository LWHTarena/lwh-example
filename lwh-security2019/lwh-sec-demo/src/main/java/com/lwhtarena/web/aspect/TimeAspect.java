package com.lwhtarena.web.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author liwh
 * @Title: TimeAspect
 * @Package com.lwhtarena.web.aspect
 * @Description:
 * @Version 1.0.0
 * @date 2020/7/20 22:32
 */
@Aspect
@Component
public class TimeAspect {

    @Around("execution(* com.lwhtarena.web.controller.UserController.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {

        System.out.println("time aspect start");

        Object[] args = pjp.getArgs();
        for (Object arg : args) {
            System.out.println("arg is "+arg);
        }

        long start = new Date().getTime();

        Object object = pjp.proceed();

        System.out.println("time aspect 耗时:"+ (new Date().getTime() - start));

        System.out.println("time aspect end");

        return object;
    }

}
