package com.careerbuilder.careerbuilder.global.common.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum PartnerErrorCode implements ErrorCodeIfs{

    PARTNER_NOT_FOUND(HttpStatus.NOT_FOUND.value(), 4500, ErrorType.CLIENT, "해당 거래처를 찾을 수 없음."),
    ;

    private final Integer httpStatusCode;
    private final Integer errorCode;
    private final ErrorType errorType;
    private final String errorMessage;
}
