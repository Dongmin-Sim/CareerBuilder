package com.careerbuilder.careerbuilder.domain.product.service.error;

import com.careerbuilder.careerbuilder.global.common.error.ErrorCodeIfs;
import com.careerbuilder.careerbuilder.global.common.error.ErrorType;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ProductErrorCode implements ErrorCodeIfs {

    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "product_not_found", 4100, ErrorType.CLIENT, "요청한 상품을 찾을 수 없습니다."),
    INVALID_REQUEST(HttpStatus.BAD_REQUEST.value(), "",4101, ErrorType.CLIENT, "요청값이 ."),
    INVALID_SEARCH_FIELD(HttpStatus.BAD_REQUEST.value(), "",4102, ErrorType.CLIENT, "검색 필드가 유효하지 않습니다")
    ;

    private final Integer httpStatusCode;
    private final String errorCode;
    private final Integer errorCodeNum;
    private final ErrorType errorType;
    private final String errorMessage;

    ProductErrorCode(Integer httpStatusCode, String errorCode, Integer errorCodeNum, ErrorType errorType, String errorMessage) {
        this.httpStatusCode = httpStatusCode;
        this.errorCode = errorCode;
        this.errorCodeNum = errorCodeNum;
        this.errorType = errorType;
        this.errorMessage = errorMessage;
    }
}
