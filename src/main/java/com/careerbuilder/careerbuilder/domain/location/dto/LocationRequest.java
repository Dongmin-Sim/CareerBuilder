package com.careerbuilder.careerbuilder.domain.location.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocationRequest {

    @NotBlank
    private String name;

    private String memo;
}
