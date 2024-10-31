package org.sopt.diary.common.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sopt.diary.api.dto.ResponseDto;
import org.sopt.diary.common.exception.BusinessErrorCode;
import org.sopt.diary.common.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = {BusinessException.class})
    public ResponseEntity<ResponseDto<BusinessErrorCode>> handleBusinessException(BusinessException e) {
        return ResponseEntity
                .status(e.getErrorCode().getHttpStatus())
                .body(ResponseDto.fail(e.getErrorCode()));
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<ResponseDto<BusinessErrorCode>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ResponseDto.validFail(Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage()));
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ResponseDto<BusinessErrorCode>> handleException(java.lang.Exception e) {
        // 예외 메시지와 스택 트레이스를 로그에 기록
        logger.error("Unhandled exception occurred: {}", e.getMessage(), e);
        return ResponseEntity
                .status(BusinessErrorCode.INTERNAL_SERVER_ERROR.getHttpStatus())
                .body(ResponseDto.fail(BusinessErrorCode.INTERNAL_SERVER_ERROR));
    }
}
