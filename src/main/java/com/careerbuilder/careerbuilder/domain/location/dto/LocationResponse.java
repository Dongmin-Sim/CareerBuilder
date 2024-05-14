package com.careerbuilder.careerbuilder.domain.location.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocationResponse {

    private Long id;

    private String name;

    private String memo;
}
