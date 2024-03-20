package com.careerbuilder.careerbuilder.global.common.exception;

import com.careerbuilder.careerbuilder.global.common.error.ErrorCodeIfs;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class ApiException extends RuntimeException implements ApiExceptionIfs{

    private final ErrorCodeIfs errorCodeIfs;
    private final String errorMessage;


    public ApiException(ErrorCodeIfs errorCodeIfs) {
        super(errorCodeIfs.getErrorMessage());

        this.errorCodeIfs = errorCodeIfs;
        this.errorMessage = errorCodeIfs.getErrorMessage();
    }

    public ApiException(ErrorCodeIfs errorCodeIfs, String errorMessage) {
        super(errorCodeIfs.getErrorMessage());

        this.errorCodeIfs = errorCodeIfs;
        this.errorMessage = errorMessage;
    }

    public ApiException(ErrorCodeIfs errorCodeIfs, Throwable tx) {
        super(tx);

        this.errorCodeIfs = errorCodeIfs;
        this.errorMessage = errorCodeIfs.getErrorMessage();
    }

    public ApiException(ErrorCodeIfs errorCodeIfs, Throwable tx, String errorMessage) {
        super(tx);

        this.errorCodeIfs = errorCodeIfs;
        this.errorMessage = errorMessage;
    }

}
