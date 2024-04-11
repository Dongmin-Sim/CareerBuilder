package com.careerbuilder.careerbuilder.global.common.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode implements ErrorCodeIfs {

    OK(HttpStatus.OK.value(), 2000, ErrorType.OK, "요청 성공"),

    BAD_REQUEST(HttpStatus.BAD_REQUEST.value(), 4000, ErrorType.CLIENT, "잘못된 요청"),
    VALIDATION_ERROR(HttpStatus.BAD_REQUEST.value(), 4001, ErrorType.CLIENT, "유효성 검사 실패"),

    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), 5000, ErrorType.SERVER, "서버 에러"),
    NULL_POINT_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), 50001, ErrorType.SERVER_REF, "Null Point Error"),
    ;

    private final Integer httpStatusCode;
    private final Integer errorCode;
    private final ErrorType errorType;
    private final String errorMessage;
}
