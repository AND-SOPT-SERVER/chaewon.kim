package org.sopt.diary.api.dto;

public class ErrorDto {

    private int code;
    private String message;

    public ErrorDto(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ErrorDto of(int code, String message) {
        return new ErrorDto(code, message);
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
