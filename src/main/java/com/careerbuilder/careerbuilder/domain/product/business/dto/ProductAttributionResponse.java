package com.careerbuilder.careerbuilder.domain.product.business.dto;


import com.careerbuilder.careerbuilder.domain.attribution.entity.Attribution;
import com.careerbuilder.careerbuilder.domain.product.db.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductAttributionResponse {

    private Long id;

    private Product product;

    private Attribution Attribution;

    private String attributionValue;
}
