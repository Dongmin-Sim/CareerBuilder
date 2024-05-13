package com.careerbuilder.careerbuilder.domain.transaction.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionUpdateRequest {

    private Long id;

    private Long fromLocation;

    private Long toLocation;

    private Long partner;

    private String memo;

    private List<Item> items;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Item {
        Long productId;
        int quantity;
    }
}
