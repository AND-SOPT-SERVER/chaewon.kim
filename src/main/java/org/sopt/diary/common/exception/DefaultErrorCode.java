package org.sopt.diary.common.exception;

import org.springframework.http.HttpStatus;

public interface DefaultErrorCode {
    HttpStatus getHttpStatus();
    int getCode();
    String getMessage();
}
