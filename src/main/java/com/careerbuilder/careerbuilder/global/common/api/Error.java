package com.careerbuilder.careerbuilder.global.common.api;

import com.careerbuilder.careerbuilder.global.common.error.ErrorCode;
import com.careerbuilder.careerbuilder.global.common.error.ErrorCodeIfs;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Error {

    private String code;
    private String message;

    public static Error ERROR(ErrorCodeIfs errorCodeIfs) {
        return Error.builder()
                .code(errorCodeIfs.getErrorCode())
                .message(errorCodeIfs.getErrorMessage())
                .build();
    }

    public static Error ERROR(ErrorCodeIfs errorCodeIfs, String errorMessage) {
        return Error.builder()
                .code(errorCodeIfs.getErrorCode())
                .message(errorMessage)
                .build();
    }


}
