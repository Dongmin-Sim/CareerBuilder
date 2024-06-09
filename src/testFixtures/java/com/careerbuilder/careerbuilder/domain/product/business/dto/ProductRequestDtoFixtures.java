package com.careerbuilder.careerbuilder.domain.product.business.dto;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductRequestDtoFixtures {

    public static ProductRequestDto.CreateProductDto createProductDto() {
        return new ProductRequestDto.CreateProductDto(
                "name",
                "barcode",
                "photoUrl",
                BigDecimal.ONE,
                BigDecimal.TEN,
                1L,
                10
        );
    }

    public static ProductRequestDto.CreateProductDto createProductDto(Long locationId, int initialQuantity) {
        return new ProductRequestDto.CreateProductDto(
                "name",
                "barcode",
                "photoUrl",
                BigDecimal.ONE,
                BigDecimal.TEN,
                locationId,
                initialQuantity
        );
    }

    public static ProductRequestDto.CreateProductDto createProductDto(
            String name,
            String barcode,
            String photoUrl,
            BigDecimal cost,
            BigDecimal price,
            Long locationId,
            int initialQuantity
            ) {
        return new ProductRequestDto.CreateProductDto(
                name,
                barcode,
                photoUrl,
                cost,
                price,
                locationId,
                initialQuantity
        );
    }

    public static ProductRequestDto.UpdateProductDto updateProductDto() {
        return new ProductRequestDto.UpdateProductDto(
                "newName",
                "newBarcode",
                "newPhotoUrl",
                BigDecimal.valueOf(1000),
                BigDecimal.valueOf(2000)
        );
    }

    public static ProductRequestDto.UpdateProductDto updateProductDto(String name, String barcode, String photoUrl) {
        return new ProductRequestDto.UpdateProductDto(
                name,
                barcode,
                photoUrl,
                BigDecimal.valueOf(1000),
                BigDecimal.valueOf(2000)
        );
    }
}
