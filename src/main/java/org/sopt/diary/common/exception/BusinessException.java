package org.sopt.diary.common.exception;

public class BusinessException extends RuntimeException {
    private final DefaultErrorCode errorCode;

    public BusinessException(DefaultErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public DefaultErrorCode getErrorCode() {
        return errorCode;
    }
}
