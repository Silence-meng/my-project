package com.silence.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author silence
 * @since 2024/12/13 15:24
 **/
@Constraint(validatedBy = ImageSizeValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ImageSize {
    String message() default "文件大小超出限制";

    long min() default 0;

    long max() default Long.MAX_VALUE;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
