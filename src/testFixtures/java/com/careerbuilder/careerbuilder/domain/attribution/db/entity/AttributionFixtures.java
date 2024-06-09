package com.careerbuilder.careerbuilder.domain.attribution.db.entity;

import com.careerbuilder.careerbuilder.domain.attribution.entity.Attribution;
import com.careerbuilder.careerbuilder.domain.attribution.entity.type.AttributionType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AttributionFixtures {

    public static final Attribution attribution(Long id) {
        return Attribution.builder()
                .id(id)
                .attributionName("name")
                .attributionType(AttributionType.STRING)
                .rankNum(1)
                .build();
    }

    public static final Attribution attribution() {
        return Attribution.builder()
                .id(1L)
                .attributionName("name")
                .attributionType(AttributionType.STRING)
                .rankNum(1)
                .build();
    }
}
