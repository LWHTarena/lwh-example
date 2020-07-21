package com.lwhtarena.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author liwh
 * @Title: MyConstraint
 * @Package com.lwhtarena.validator
 * @Description:
 * @Version 1.0.0
 * @date 2020/7/20 22:29
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MyConstraintValidator.class)
public @interface MyConstraint {

    String message();

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    String field() default "";

}
