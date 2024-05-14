package com.careerbuilder.careerbuilder.domain.partner.entity.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PartnerType {

    IN("입고처"), OUT("출고처"),
    ;

    private final String description;
}
