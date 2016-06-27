package io.hbprotoss.web.validator.annotation;

import io.hbprotoss.web.validator.DateValidator;

import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by hbprotoss on 6/15/16.
 */
@Target({ FIELD, METHOD, PARAMETER, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = DateValidator.class)
@Documented
public @interface CheckDate {
    String pattern() default "yyyy-MM-dd";
    boolean required() default false;
    String message() default "error field";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}

