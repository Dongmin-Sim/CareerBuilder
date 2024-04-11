package com.careerbuilder.careerbuilder.domain.attribution.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateAttributionRankRequest {
    @NotNull
    private Integer rankNum;
}
