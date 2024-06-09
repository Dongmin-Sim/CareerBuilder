package com.careerbuilder.careerbuilder.domain.stock.dto;

import com.careerbuilder.careerbuilder.domain.location.dto.LocationResponse;
import com.careerbuilder.careerbuilder.domain.product.business.dto.ProductResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockDetailResponse {

    private LocationResponse location;
    private Map<ProductResponseDto.ProductDto, Integer> productQuantityMap;
    private Integer totalQuantity;
}
