package com.careerbuilder.careerbuilder.global.common.api;


import com.careerbuilder.careerbuilder.global.common.error.ErrorCodeIfs;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Api<T>{

    private Result result;

    private T body;

    public static <T> Api<T> OK(T data) {
        return Api.<T>builder()
                .result(Result.OK())
                .body(data)
                .build();
    }

    public static <T> Api<T> Error(Result result) {
        return Api.<T>builder()
                .result(result)
                .build();
    }

    public static <T> Api<T> Error(ErrorCodeIfs errorCodeIfs) {
        return Api.<T>builder()
                .result(Result.ERROR(errorCodeIfs))
                .build();
    }

    public static <T> Api<T> Error(ErrorCodeIfs errorCodeIfs, String errorMessage) {
        return Api.<T>builder()
                .result(Result.ERROR(errorCodeIfs, errorMessage))
                .build();
    }
}
