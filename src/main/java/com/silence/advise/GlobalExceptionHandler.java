package com.silence.advise;

import com.silence.enetity.BaseResp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.LocaleResolver;

/**
 * @author silence
 * @since 2024/10/9 09:44
 **/
@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    private LocaleResolver localeResolver;

    /**
     * 处理参数校验异常
     *
     * @param e 异常
     * @return 异常信息
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResp<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("参数校验失败", e);
        String errorMessage = messageSource.getMessage("parameter.not.valid", null,
                LocaleContextHolder.getLocale());
        if (!e.getBindingResult().getFieldErrors().isEmpty()) {
            var fieldError = e.getBindingResult().getFieldError();
            assert fieldError != null;
            errorMessage = fieldError.getDefaultMessage();
        }

        return BaseResp.fail(errorMessage);
    }
}
