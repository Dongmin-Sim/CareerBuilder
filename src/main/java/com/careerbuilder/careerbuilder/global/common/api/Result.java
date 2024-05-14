package com.careerbuilder.careerbuilder.global.common.api;

import com.careerbuilder.careerbuilder.global.common.error.ErrorCode;
import com.careerbuilder.careerbuilder.global.common.error.ErrorCodeIfs;
import com.careerbuilder.careerbuilder.global.common.error.ErrorType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Result {

    private Integer resultCode;

    private ErrorType resultType;

    private String resultMessage;

    private String description;


    public static Result OK() {
        return Result.builder()
                .resultCode(ErrorCode.OK.getErrorCode())
                .resultType(ErrorCode.OK.getErrorType())
                .resultMessage(ErrorCode.OK.getErrorMessage())
                .description("요청 성공")
                .build();
    }

    public static Result ERROR(ErrorCodeIfs errorCodeIfs) {
        return Result.builder()
                .resultCode(errorCodeIfs.getErrorCode())
                .resultType(errorCodeIfs.getErrorType())
                .resultMessage(errorCodeIfs.getErrorMessage())
                .description("에러 발생")
                .build();
    }

    public static Result ERROR(ErrorCodeIfs errorCodeIfs, String errorDescription) {
        return Result.builder()
                .resultCode(errorCodeIfs.getErrorCode())
                .resultType(errorCodeIfs.getErrorType())
                .resultMessage(errorCodeIfs.getErrorMessage())
                .description(errorDescription)
                .build();
    }


}
