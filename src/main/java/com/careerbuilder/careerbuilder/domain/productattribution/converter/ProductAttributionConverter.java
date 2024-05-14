package com.careerbuilder.careerbuilder.domain.productattribution.converter;

import com.careerbuilder.careerbuilder.domain.productattribution.dto.ProductAttributionResponse;
import com.careerbuilder.careerbuilder.domain.productattribution.entity.ProductAttribution;
import com.careerbuilder.careerbuilder.global.common.annotation.Converter;
import com.careerbuilder.careerbuilder.global.common.error.ErrorCode;
import com.careerbuilder.careerbuilder.global.common.exception.ApiException;

import java.util.Optional;

@Converter
public class ProductAttributionConverter {

    public ProductAttributionResponse toResponse(
            ProductAttribution productAttribution
    ) {
        return Optional.ofNullable(productAttribution)
                .map(it -> {
                    return ProductAttributionResponse.builder()
                            .id(productAttribution.getId())
                            .product(productAttribution.getProduct())
                            .Attribution(productAttribution.getAttribution())
                            .attributionValue(productAttribution.getAttributionValue())
                            .build();
                }).orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT_ERROR));
    }
}
