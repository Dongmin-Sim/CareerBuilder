package com.careerbuilder.careerbuilder.global.common.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum TransactionError implements ErrorCodeIfs{

    VALIDATION_ERROR(HttpStatus.BAD_REQUEST.value(), "",4300, ErrorType.CLIENT, "유효하지 않은 요청"),
    TRANSACTION_TYPE_ERROR(HttpStatus.BAD_REQUEST.value(), "invalid_transaction_type", 4301, ErrorType.CLIENT, "지원하지 않는 거래 유형입니다."),
    ;

    private final Integer httpStatusCode;
    private final String errorCode;
    private final Integer errorCodeNum;
    private final ErrorType errorType;
    private final String errorMessage;
}
