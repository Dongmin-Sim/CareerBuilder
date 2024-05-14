package com.careerbuilder.careerbuilder.domain.transactionItem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionItemResponse {

    private Long id;
    private Long transactionId;
    private Long productId;
    private int quantity;
}
