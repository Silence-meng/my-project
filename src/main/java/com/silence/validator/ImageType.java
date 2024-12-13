package com.silence.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author silence
 * @since 2024/12/13 15:04
 **/
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ImageTypeValidator.class)
public @interface ImageType {

    String message() default "Invalid image type.";

    Class[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String[] value();
}
