package com.careerbuilder.careerbuilder.domain.stock.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockResponse {

    private Long id;
    private Long locationId;
    private Long productId;
    private int stockQuantity;
}
