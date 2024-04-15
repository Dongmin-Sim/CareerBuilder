package com.careerbuilder.careerbuilder.global.common.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum LocationErrorCode implements ErrorCodeIfs {

    LOCATION_NOT_FOUND(HttpStatus.NOT_FOUND.value(), 4400, ErrorType.CLIENT, "해당 위치를 찾을 수 없음."),
    ;

    private final Integer httpStatusCode;
    private final Integer errorCode;
    private final ErrorType errorType;
    private final String errorMessage;
}
