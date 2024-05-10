package com.careerbuilder.careerbuilder.domain.transactionproduct.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionProductResponse {

    private Long id;
    private Long transactionId;
    private Long productId;
    private int quantity;
}
