package com.careerbuilder.careerbuilder.domain.product.dto;

import com.careerbuilder.careerbuilder.domain.productattribution.dto.ProductAttributionResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductWithAttributionsResponse {

    private ProductResponse productResponse;

    private List<ProductAttributionResponse> attributionResponseList;
}
