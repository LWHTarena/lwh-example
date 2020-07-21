package com.lwhtarena.validator;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.springframework.validation.BindingResult;

/**
 * @author liwh
 * @Title: ValidateAspect
 * @Package com.lwhtarena.validator
 * @Description:
 * @Version 1.0.0
 * @date 2020/7/20 22:29
 */
//@Aspect
//@Component
public class ValidateAspect {

    @Around("execution(* com.imooc.web.controller.UserController.*(..))")
    public Object handleValidateResult(ProceedingJoinPoint pjp) throws Throwable {

        System.out.println("进入切片");

        Object[] args = pjp.getArgs();
        for (Object arg : args) {
            if(arg instanceof BindingResult) {
                BindingResult errors = (BindingResult)arg;
                if (errors.hasErrors()) {
                    throw new ValidateException(errors.getAllErrors());
                }
            }
        }

        Object result = pjp.proceed();

        return result;
    }

}
