package com.careerbuilder.careerbuilder.domain.product.business.dto;

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
