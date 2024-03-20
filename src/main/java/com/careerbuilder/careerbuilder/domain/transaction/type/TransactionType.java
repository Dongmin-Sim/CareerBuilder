package com.careerbuilder.careerbuilder.domain.transaction.type;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum TransactionType {
    WAREHOUSING("입고"), DELIVERY("출고"), ADJUSTMENT("조정"), MOVING("이동"),
    ;

    private final String name;
}