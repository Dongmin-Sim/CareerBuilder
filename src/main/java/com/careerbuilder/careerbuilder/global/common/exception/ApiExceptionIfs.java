package com.careerbuilder.careerbuilder.global.common.exception;

import com.careerbuilder.careerbuilder.global.common.error.ErrorCodeIfs;

public interface ApiExceptionIfs {

    ErrorCodeIfs getErrorCodeIfs();

    String getErrorMessage();
}
