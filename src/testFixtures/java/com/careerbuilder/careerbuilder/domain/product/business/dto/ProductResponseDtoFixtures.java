package com.careerbuilder.careerbuilder.domain.product.business.dto;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductResponseDtoFixtures {

    public static final ProductResponseDto.ProductDto productDto() {
        return new ProductResponseDto.ProductDto(
                1L,
                "name",
                "barcode",
                "photoUrl",
                BigDecimal.ONE,
                BigDecimal.TEN
        );
    }

    public static final ProductResponseDto.ProductDto productDto(Long id) {
        return new ProductResponseDto.ProductDto(
                id,
                "name",
                "barcode",
                "photoUrl",
                BigDecimal.ONE,
                BigDecimal.TEN
        );
    }

    public static final ProductResponseDto.ProductDto productDto(Long id, String name) {
        return new ProductResponseDto.ProductDto(
                id,
                name,
                "barcode",
                "photoUrl",
                BigDecimal.ONE,
                BigDecimal.TEN
        );
    }

    public static final ProductResponseDto.ProductDto productDto(Long id, String name, String barcode, String photoUrl) {
        return new ProductResponseDto.ProductDto(
                id,
                name,
                barcode,
                photoUrl,
                BigDecimal.ONE,
                BigDecimal.TEN
        );
    }


}
