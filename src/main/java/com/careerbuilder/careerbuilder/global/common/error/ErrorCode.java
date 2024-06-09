package com.careerbuilder.careerbuilder.global.common.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode implements ErrorCodeIfs {

    OK(HttpStatus.OK.value(), "ok", 2000, ErrorType.OK, "요청 성공"),

    VALIDATION_ERROR(HttpStatus.BAD_REQUEST.value(), "invalid_parameter", 4001, ErrorType.CLIENT, "파라미터는 제공되었지만, 해당 값이 올바르지 않음."),
    MISSING_ERROR(HttpStatus.BAD_REQUEST.value(), "missing_parameter", 4002, ErrorType.CLIENT, "필요한 파라미터가 제공되지 않았음."),
    BAD_REQUEST(HttpStatus.BAD_REQUEST.value(), "bad_request", 4999, ErrorType.CLIENT, "알 수 없는 클라이언트 요청 에러"),

    NULL_POINT_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "internal_server_error", 50001, ErrorType.SERVER_REF, "Null Point Error"),
    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "internal_server_error", 5999, ErrorType.SERVER, "알 수 없는 서버 에러"),
    ;

    private final Integer httpStatusCode;
    private final String errorCode;
    private final Integer errorCodeNum;
    private final ErrorType errorType;
    private final String errorMessage;
}
