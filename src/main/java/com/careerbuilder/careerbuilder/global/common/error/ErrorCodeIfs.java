package com.careerbuilder.careerbuilder.global.common.error;

public interface ErrorCodeIfs {

    Integer getHttpStatusCode();

    Integer getErrorCode();

    ErrorType getErrorType();

    String getErrorMessage();
}
