package com.careerbuilder.careerbuilder.domain.transaction.entity.type;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum TransactionStatus {
    ACTIVE("활성화"), MODIFIED("수정"),  DELETED("삭제");

    private final String description;
}
