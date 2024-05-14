package com.careerbuilder.careerbuilder.domain.location.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocationRequest {

    @NotBlank
    private String name;

    private String memo;
}
