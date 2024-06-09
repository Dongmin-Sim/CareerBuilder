package com.careerbuilder.careerbuilder.global.exceptionhandler;

import com.careerbuilder.careerbuilder.global.common.api.Api;
import com.careerbuilder.careerbuilder.global.common.error.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

@Slf4j
@RestControllerAdvice
@Order(value = 9)
public class ValidationExceptionHandler {
    @ExceptionHandler({HandlerMethodValidationException.class, MethodArgumentNotValidException.class})
    public ResponseEntity<Api<Object>> validationException(
            Exception exception
    ) {
        // TODO Exception 메시지 포맷 정리 필요
        log.error("Validation Exception : {}", exception.getMessage());

        return ResponseEntity
                .status(ErrorCode.VALIDATION_ERROR.getHttpStatusCode())
                .body(Api.ERROR(ErrorCode.VALIDATION_ERROR));
    }
}
