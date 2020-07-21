package com.lwhtarena.validator;

import com.lwhtarena.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author liwh
 * @Title: MyConstraintValidator
 * @Package com.lwhtarena.validator
 * @Description:
 * @Version 1.0.0
 * @date 2020/7/20 22:29
 */
public class MyConstraintValidator implements ConstraintValidator<MyConstraint, Object> {

    @Autowired
    private HelloService helloService;

    @Override
    public void initialize(MyConstraint constraintAnnotation) {
        System.out.println("my validator init");
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        helloService.greeting("tom");
        System.out.println(value);
        return true;
    }

}
