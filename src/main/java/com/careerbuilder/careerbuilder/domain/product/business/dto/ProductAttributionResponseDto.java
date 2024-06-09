package com.careerbuilder.careerbuilder.domain.product.business.dto;


import com.careerbuilder.careerbuilder.domain.attribution.dto.AttributionResponseDto;
import com.careerbuilder.careerbuilder.domain.attribution.entity.Attribution;
import com.careerbuilder.careerbuilder.domain.attribution.entity.type.AttributionType;
import com.careerbuilder.careerbuilder.domain.product.db.entity.Product;

public class ProductAttributionResponseDto {
    public record ProductAttributionDto(
            Long id,
            Product product,
            Attribution attribution,
            String value
    ) {}

    public record AttributionValueResponseDto(
            AttributionResponseDto.AttributionResponse attribution,
            String value
    ) {}

}
