package com.careerbuilder.careerbuilder.domain.product.business.dto;


import com.careerbuilder.careerbuilder.domain.product.db.entity.Product;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

import static com.careerbuilder.careerbuilder.domain.product.business.dto.ProductAttributionResponseDto.*;

public class ProductResponseDto {
    public record ProductDto(
            Long id,
            String name,
            String barcode,
            String photoUrl,
            BigDecimal cost,
            BigDecimal price
    ) {
        public static ProductDto fromDomain(Product product) {
            return new ProductDto(
                    product.getId(),
                    product.getName(),
                    product.getBarcode(),
                    product.getPhotoUrl(),
                    product.getCost(),
                    product.getPrice()
            );
        }
    }

    public record ProductWithAttributionDto(
            @JsonProperty(value = "product")
            ProductDto product,
            @JsonProperty(value = "productAttributions")
            List<AttributionValueResponseDto> productAttributions
    ) { }
}
