package com.careerbuilder.careerbuilder.domain.product.business.dto;

import com.careerbuilder.careerbuilder.domain.attribution.entity.Attribution;
import com.careerbuilder.careerbuilder.domain.product.business.dto.ProductAttributionResponseDto.ProductAttributionDto;
import com.careerbuilder.careerbuilder.domain.product.db.entity.Product;

public class ProductAttributionResponseDtoFixtures {
    public static final ProductAttributionDto productAttributionDto(Long id, Product product, Attribution attribution) {
        return new ProductAttributionDto(
                id,
                product,
                attribution,
                "value"
        );
    }

    public static final ProductAttributionDto productAttributionDto(Product product, Attribution attribution) {
        return new ProductAttributionDto(
                1L,
                product,
                attribution,
                "value"
        );
    }
}
