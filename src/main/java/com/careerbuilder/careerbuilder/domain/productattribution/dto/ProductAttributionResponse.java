package com.careerbuilder.careerbuilder.domain.productattribution.dto;


import com.careerbuilder.careerbuilder.domain.attribution.entity.Attribution;
import com.careerbuilder.careerbuilder.domain.product.entity.Product;
import jakarta.persistence.*;
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
