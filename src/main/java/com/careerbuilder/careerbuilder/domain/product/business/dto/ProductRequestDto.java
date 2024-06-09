package com.careerbuilder.careerbuilder.domain.product.business.dto;

import com.careerbuilder.careerbuilder.domain.product.db.entity.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class ProductRequestDto {

    public record CreateProductDto(
            @NotBlank
            @Size(min = 2, max = 255)
            String name,
            String barcode,
            String photoUrl,
            @NotNull
            BigDecimal cost,
            @NotNull
            BigDecimal price,
            @NotNull
            Long locationId,
            @NotNull
            int initialQuantity
    ) {
        public Product toDomain() {
            return Product.builder()
                    .name(this.name)
                    .barcode(this.barcode)
                    .photoUrl(this.photoUrl)
                    .cost(this.cost)
                    .price(this.price)
                    .build();
        }
    }

    public record UpdateProductDto(
            @NotNull
            String name,
            String barcode,
            String photoUrl,
            @NotNull
            BigDecimal cost,
            @NotNull
            BigDecimal price
    ) { }

    public record SearchProductDto(
            @NotBlank
            String keyword,
            String field
    ) { }
}
