package com.careerbuilder.careerbuilder.domain.location.converter;

import com.careerbuilder.careerbuilder.domain.location.dto.LocationRequest;
import com.careerbuilder.careerbuilder.domain.location.dto.LocationResponse;
import com.careerbuilder.careerbuilder.domain.location.entity.Location;
import com.careerbuilder.careerbuilder.global.common.annotation.Converter;
import com.careerbuilder.careerbuilder.global.common.error.ErrorCode;
import com.careerbuilder.careerbuilder.global.common.exception.ApiException;

import java.util.Optional;

@Converter
public class LocationConverter {

    public Location toEntity(LocationRequest request) {
        return Optional.ofNullable(request)
                .map(it -> {
                    return Location.builder()
                            .name(request.getName())
                            .memo(request.getMemo())
                            .build();
                }).orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT_ERROR));
    }

    public LocationResponse toResponse(Location entity) {
        return Optional.ofNullable(entity)
                .map(it -> {
                    return LocationResponse.builder()
                            .id(entity.getId())
                            .name(entity.getName())
                            .memo(entity.getMemo())
                            .build();
                }).orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT_ERROR));
    }
}
