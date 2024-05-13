package com.careerbuilder.careerbuilder.domain.stock.dto;

import com.careerbuilder.careerbuilder.domain.transaction.dto.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockRequest {

    private Long locationId;
    private Long productId;
    private int stockQuantity;

    public static StockRequest of(Long locationId, Long productId, int quantity) {
        return StockRequest.builder()
                .locationId(locationId)
                .productId(productId)
                .stockQuantity(quantity)
                .build();
    }

    public static StockRequest of(Long locationId, Item item) {
        return StockRequest.builder()
                .locationId(locationId)
                .productId(item.getProductId())
                .stockQuantity(item.getQuantity())
                .build();
    }
}
