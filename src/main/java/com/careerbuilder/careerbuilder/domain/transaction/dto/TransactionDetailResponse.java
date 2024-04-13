package com.careerbuilder.careerbuilder.domain.transaction.dto;

import com.careerbuilder.careerbuilder.domain.transaction.entity.type.TransactionStatusType;
import com.careerbuilder.careerbuilder.domain.transaction.entity.type.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionDetailResponse {

    private Long id;

    private TransactionType transactionType;

    private Long fromLocationId;

    private Long toLocationId;

    private Long partnerId;

    private String memo;

    private TransactionStatusType status;

    private List<Item> items;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Item {
        private Long productId;
        private Integer quantity;
    }
}
