package com.careerbuilder.careerbuilder.global.exceptionhandler;

import com.careerbuilder.careerbuilder.global.common.api.Api;
import com.careerbuilder.careerbuilder.global.common.error.ErrorCodeIfs;
import com.careerbuilder.careerbuilder.global.common.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class ApiExceptionHandler {

    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity<Api<Object>> apiException(
            ApiException apiException
    ) {
        log.error("[Global exception] : ", apiException);

        ErrorCodeIfs errorCodeIfs = apiException.getErrorCodeIfs();

        return ResponseEntity
                .status(errorCodeIfs.getHttpStatusCode())
                .body(Api.ERROR(errorCodeIfs, apiException.getErrorMessage()));
    }

}
