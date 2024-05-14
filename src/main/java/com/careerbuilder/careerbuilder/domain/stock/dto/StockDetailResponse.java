package com.careerbuilder.careerbuilder.domain.stock.dto;

import com.careerbuilder.careerbuilder.domain.location.dto.LocationResponse;
import com.careerbuilder.careerbuilder.domain.product.dto.ProductResponse;
import com.careerbuilder.careerbuilder.domain.product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockDetailResponse {

    private LocationResponse location;
    private Map<ProductResponse, Integer> productQuantityMap;
    private Integer totalQuantity;
}
