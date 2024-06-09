package com.careerbuilder.careerbuilder.domain.product.business.dto;


import com.careerbuilder.careerbuilder.domain.attribution.entity.Attribution;
import com.careerbuilder.careerbuilder.domain.product.db.entity.Product;

public class ProductAttributionRequestDto {

    public record CreateProductAttributionDto(
            Product product,
            Attribution Attribution,
            String attributionValue
    ) {}

    public record UpdateProductAttributionDto(
            Long id,
            String attributionValue
    ) {}
}
