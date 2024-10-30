package org.sopt.diary.common.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    //400 BAD_REQUEST
    BAD_REQUEST(HttpStatus.BAD_REQUEST,40000, "잘못된 요청입니다."),
    //404 NOT_FOUND
    DIARY_NOT_FOUND(HttpStatus.NOT_FOUND,40400,"해당 다이어리를 찾을 수 없습니다."),
    // 500 INTERNAL_SEVER_ERROR
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,50000, "서버 내부 오류입니다."),
    ;

    private HttpStatus httpStatus;
    private int code;
    private String message;

    ErrorCode(HttpStatus httpStatus, int code, String message) {
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
