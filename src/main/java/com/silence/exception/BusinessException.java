package com.silence.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * @author silence
 * @since 2024/9/12 09:57
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class BusinessException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer errorCode;

    private String errorMsg;

    public BusinessException() {
        super();
    }

    public BusinessException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    public BusinessException(Integer errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public BusinessException(Integer errorCode, String errorMsg, Throwable cause) {
        super(errorMsg, cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public BusinessException(ErrorEnum error) {
        super(error.getErrorMsg());
        this.errorCode = error.getErrorCode();
        this.errorMsg = error.getErrorMsg();
    }

    @Override
    public String getMessage() {
        return errorMsg;
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
