package com.careerbuilder.careerbuilder.domain.attribution.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateAttributionNameRequest {
    @NotBlank
    private String attributionName;
}
