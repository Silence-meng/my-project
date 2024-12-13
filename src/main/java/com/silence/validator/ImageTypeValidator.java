package com.silence.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author silence
 * @since 2024/12/13 15:06
 **/
public class ImageTypeValidator implements ConstraintValidator<ImageType, MultipartFile> {

    private String[] allowedTypes;

    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext constraintValidatorContext) {
        if (multipartFile == null || multipartFile.isEmpty()) {
            // 空文件不做校验
            return true;
        }

        String contentType = multipartFile.getContentType();
        for (String allowedType : allowedTypes) {
            if (allowedType.equalsIgnoreCase(contentType)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void initialize(ImageType constraintAnnotation) {
        this.allowedTypes = constraintAnnotation.value();
    }
}
