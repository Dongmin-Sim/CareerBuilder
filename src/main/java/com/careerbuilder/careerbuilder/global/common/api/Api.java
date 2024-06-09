package com.careerbuilder.careerbuilder.global.common.api;


import com.careerbuilder.careerbuilder.global.common.error.ErrorCodeIfs;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Api<T> {

    private boolean success;
    private Error error;
    private T data;

    public static <T> Api<T> OK(T data) {
        return Api.<T>builder()
                .success(true)
                .error(null)
                .data(data)
                .build();
    }

    public static <T> Api<T> ERROR(Error error) {
        return Api.<T>builder()
                .success(false)
                .error(error)
                .data(null)
                .build();
    }

    public static <T> Api<T> ERROR(ErrorCodeIfs errorCodeIfs) {
        return Api.<T>builder()
                .success(false)
                .error(Error.ERROR(errorCodeIfs))
                .data(null)
                .build();
    }
}
