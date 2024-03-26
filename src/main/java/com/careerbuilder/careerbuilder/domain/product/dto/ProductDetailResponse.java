package com.careerbuilder.careerbuilder.domain.product.dto;

import com.careerbuilder.careerbuilder.domain.attribution.dto.AttributionResponse;
import com.careerbuilder.careerbuilder.domain.attribution.entity.Attribution;
import com.careerbuilder.careerbuilder.domain.productattribution.dto.ProductAttributionResponse;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDetailResponse {

    private ProductResponse productResponse;

    private List<ProductAttributionResponse> attributionResponseList;
}
