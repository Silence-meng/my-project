package com.silence.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author silence
 * @since 2024/12/13 15:26
 **/
public class ImageSizeValidator implements ConstraintValidator<ImageSize, MultipartFile> {

    private long min;

    private long max;

    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext constraintValidatorContext) {
        if (multipartFile == null || multipartFile.isEmpty()) {
            // 可以根据需要调整是否允许为空
            return true;
        }
        long size = multipartFile.getSize();

        return size >= min && size <= max;
    }

    @Override
    public void initialize(ImageSize constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }
}
