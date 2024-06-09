package com.careerbuilder.careerbuilder.domain.attribution.dto;

import com.careerbuilder.careerbuilder.domain.attribution.entity.Attribution;
import com.careerbuilder.careerbuilder.domain.attribution.entity.type.AttributionType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttributionResponseDto {

    public record AttributionResponse(
            Long id,
            @JsonProperty(value = "type")
            AttributionType attributionType,
            @JsonProperty(value = "name")
            String attributionName,
            Integer rankNum
    ) {
        public static AttributionResponse fromDomain(Attribution attribution) {
            return new AttributionResponse(
                    attribution.getId(),
                    attribution.getAttributionType(),
                    attribution.getAttributionName(),
                    attribution.getRankNum()
            );
        }
    }

    private Long id;
    @JsonProperty(value = "type")
    private AttributionType attributionType;
    @JsonProperty(value = "name")
    private String attributionName;

    private Integer rankNum;
}
