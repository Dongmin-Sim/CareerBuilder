package com.careerbuilder.careerbuilder.global.common.error;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ErrorType {
    OK("성공"),
    CLIENT("클라이언트 관련 에러"),
    SERVER("서버 관련 에러"),
    SERVER_REF("내부 참조 에러"),
    AUTHENTICATE("인증 관련 에러");

    private final String description;
}
