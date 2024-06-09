package com.careerbuilder.careerbuilder.domain.product.db.entity;

import com.careerbuilder.careerbuilder.domain.attribution.entity.Attribution;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductAttributionFixtures {
    public static final ProductAttribution productAttribution(Long id, Product product, Attribution attribution) {
        return ProductAttribution.builder()
                .id(id)
                .productId(product.getId())
                .attributionId(attribution.getId())
                .attributionValue("value")
                .build();
    }

    public static final ProductAttribution productAttribution(Product product, Attribution attribution) {
        return ProductAttribution.builder()
                .id(1L)
                .productId(product.getId())
                .attributionId(attribution.getId())
                .attributionValue("value")
                .build();
    }
}
