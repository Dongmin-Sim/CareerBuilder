package com.careerbuilder.careerbuilder.domain.partner.converter;

import com.careerbuilder.careerbuilder.domain.partner.dto.PartnerRegisterRequest;
import com.careerbuilder.careerbuilder.domain.partner.dto.PartnerResponse;
import com.careerbuilder.careerbuilder.domain.partner.entity.Partner;
import com.careerbuilder.careerbuilder.global.common.annotation.Converter;
import com.careerbuilder.careerbuilder.global.common.error.ErrorCode;
import com.careerbuilder.careerbuilder.global.common.exception.ApiException;

import java.util.Optional;

@Converter
public class PartnerConverter {

    public Partner toEntity(PartnerRegisterRequest registerRequest) {
        return Optional.ofNullable(registerRequest)
                .map(it->{
                    return Partner.builder()
                            .partnerType(registerRequest.getPartnerType())
                            .name(registerRequest.getName())
                            .phoneNumber(registerRequest.getPhoneNumber())
                            .email(registerRequest.getEmail())
                            .address(registerRequest.getAddress())
                            .build();
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT_ERROR));
    }

    public PartnerResponse toResponse(Partner entity) {
        return Optional.ofNullable(entity)
                .map(it->{
                    return PartnerResponse.builder()
                            .id(entity.getId())
                            .partnerType(entity.getPartnerType())
                            .name(entity.getName())
                            .phoneNumber(entity.getPhoneNumber())
                            .email(entity.getEmail())
                            .address(entity.getAddress())
                            .build();
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT_ERROR));

    }
}
