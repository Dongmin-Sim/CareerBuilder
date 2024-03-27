package com.careerbuilder.careerbuilder.domain.attribution.dto;

import com.careerbuilder.careerbuilder.domain.attribution.entity.type.AttributionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttributionResponse {

    private Long id;

    private AttributionType attributionType;

    private String attributionName;

    private Integer rankNum;
}
