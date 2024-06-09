package com.careerbuilder.careerbuilder.domain.product.db.entity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class
ProductFixtures {

    public static Product product(Long id) {
        return Product.builder()
                .id(id)
                .name("name")
                .barcode("barcode")
                .photoUrl("photoUrl")
                .cost(BigDecimal.ONE)
                .price(BigDecimal.TEN)
                .build();
    }

    public static Product product(Long id, String name) {
        return Product.builder()
                .id(id)
                .name(name)
                .barcode("barcode")
                .photoUrl("photoUrl")
                .cost(BigDecimal.ONE)
                .price(BigDecimal.TEN)
                .build();
    }

    public static Product product() {
        return Product.builder()
                .id(1L)
                .name("name")
                .barcode("barcode")
                .photoUrl("photoUrl")
                .cost(BigDecimal.ONE)
                .price(BigDecimal.TEN)
                .build();
    }

}