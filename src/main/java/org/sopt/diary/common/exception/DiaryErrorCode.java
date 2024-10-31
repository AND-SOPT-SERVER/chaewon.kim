package org.sopt.diary.common.exception;

import org.springframework.http.HttpStatus;

public enum DiaryErrorCode implements DefaultErrorCode {
    //404 NOT_FOUND
    DIARY_NOT_FOUND(HttpStatus.NOT_FOUND,40420,"해당 다이어리를 찾을 수 없습니다."),
    ;

    private HttpStatus httpStatus;
    private int code;
    private String message;

    DiaryErrorCode(HttpStatus httpStatus, int code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
