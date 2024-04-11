package com.careerbuilder.careerbuilder.domain.location.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocationResponse {

    private Long id;

    private String name;

    private String memo;
}
