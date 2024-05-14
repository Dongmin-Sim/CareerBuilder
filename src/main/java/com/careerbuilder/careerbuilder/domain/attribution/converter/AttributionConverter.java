package com.careerbuilder.careerbuilder.domain.attribution.converter;

import com.careerbuilder.careerbuilder.domain.attribution.dto.RegisterAttributionRequest;
import com.careerbuilder.careerbuilder.domain.attribution.dto.AttributionResponse;
import com.careerbuilder.careerbuilder.domain.attribution.entity.Attribution;
import com.careerbuilder.careerbuilder.global.common.annotation.Converter;
import com.careerbuilder.careerbuilder.global.common.error.ErrorCode;
import com.careerbuilder.careerbuilder.global.common.exception.ApiException;

import java.util.Optional;

@Converter
public class AttributionConverter {

    public Attribution toEntity(RegisterAttributionRequest request) {
        return Optional.ofNullable(request)
                .map(it -> {
                    return Attribution.builder()
                            .attributionType(request.getAttributionType())
                            .attributionName(request.getAttributionName())
                            .rankNum(request.getRankNum())
                            .build();
                }).orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT_ERROR));
    }

    public AttributionResponse toResponse(
            Attribution attribution
    ) {
        return AttributionResponse.builder()
                .id(attribution.getId())
                .attributionType(attribution.getAttributionType())
                .attributionName(attribution.getAttributionName())
                .rankNum(attribution.getRankNum())
                .build();
    }
}
