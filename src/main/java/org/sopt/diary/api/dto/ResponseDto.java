package org.sopt.diary.api.dto;

import org.sopt.diary.common.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class ResponseDto<T> {

    private boolean success;
    private T data;
    ErrorDto errorDto;

    public ResponseDto(boolean success, T data, ErrorDto errorDto) {
        this.success = success;
        this.data = data;
        this.errorDto = errorDto;
    }

    public static <T> ResponseDto<T> success() {
        return new ResponseDto<T>(true, null, null);
    }

    public static <T> ResponseDto<T> success(T data) {
        return new ResponseDto<T>(true, data, null);
    }

    public static <T> ResponseDto<T> fail(ErrorCode errorCode) {
        return new ResponseDto<T>(false, null, ErrorDto.of(errorCode.getCode(), errorCode.getMessage()));
    }

    public static <T> ResponseDto<T> validFail(String errorMessage) {
        return new ResponseDto<T>(false, null, ErrorDto.of(HttpStatus.BAD_REQUEST.value(), errorMessage));
    }

    public boolean isSuccess() {
        return success;
    }

    public T getData() {
        return data;
    }

    public ErrorDto getErrorDto() {
        return errorDto;
    }
}
