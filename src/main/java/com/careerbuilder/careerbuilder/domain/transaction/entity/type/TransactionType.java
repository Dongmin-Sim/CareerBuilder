package com.careerbuilder.careerbuilder.domain.transaction.entity.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TransactionType {
    IN("입고"), OUT("출고"), ADJUST("조정"), MOVE("이동"),
    ;

    private final String description;
}