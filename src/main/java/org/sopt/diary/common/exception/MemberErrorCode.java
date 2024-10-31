package org.sopt.diary.common.exception;

import org.springframework.http.HttpStatus;

public enum MemberErrorCode implements DefaultErrorCode {
    //401 UNAUTHORIZED
    MEMBER_UNAUTHORIZED(HttpStatus.UNAUTHORIZED, 40110, "로그인에 실패했습니다."),
    //404 NOT_FOUND
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, 40410, "유저를 찾을 수 없습니다."),
    //409 CONFLICT
    USERNAME_DUPLICATE_CONFLICT(HttpStatus.CONFLICT, 40910, "이미 가입된 유저이름입니다."),
    NICKNAME_DUPLICATE_CONFLICT(HttpStatus.CONFLICT, 40911, "이미 존재하는 닉네임입니다.")
    ;

    private HttpStatus httpStatus;
    private int code;
    private String message;

    MemberErrorCode(HttpStatus httpStatus, int code, String message) {
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
