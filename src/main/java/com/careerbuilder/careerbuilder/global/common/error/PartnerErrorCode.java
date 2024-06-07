package com.careerbuilder.careerbuilder.global.common.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum PartnerErrorCode implements ErrorCodeIfs{

    PARTNER_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "partner_not_found", 4500, ErrorType.CLIENT, "해당 거래처를 찾을 수 없습니다."),
    ;

    private final Integer httpStatusCode;
    private final String errorCode;
    private final Integer errorCodeNum;
    private final ErrorType errorType;
    private final String errorMessage;
}
