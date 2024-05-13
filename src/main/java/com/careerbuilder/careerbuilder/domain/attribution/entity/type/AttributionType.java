package com.careerbuilder.careerbuilder.domain.attribution.entity.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AttributionType {
    STRING("텍스트"), INT("숫자"), DATETIME("날짜"),;

    private final String description;
}
