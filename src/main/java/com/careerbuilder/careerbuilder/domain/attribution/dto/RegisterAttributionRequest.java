package com.careerbuilder.careerbuilder.domain.attribution.dto;

import com.careerbuilder.careerbuilder.domain.attribution.entity.type.AttributionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterAttributionRequest {

    @NotNull
    private AttributionType attributionType;

    @NotBlank
    private String attributionName;

    @NotNull
    private Integer rankNum;
}
