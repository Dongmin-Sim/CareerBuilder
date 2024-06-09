package com.careerbuilder.careerbuilder.global.common.error;

public interface ErrorCodeIfs {

    Integer getHttpStatusCode();

    String getErrorCode();

    Integer getErrorCodeNum();

    ErrorType getErrorType();

    String getErrorMessage();
}
