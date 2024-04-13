package com.careerbuilder.careerbuilder.domain.transactionproduct.dto;

import com.careerbuilder.careerbuilder.domain.product.entity.Product;
import com.careerbuilder.careerbuilder.domain.transaction.entity.Transaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionProductRequest {

    private Long transactionId;

    private Long productId;

    private int quantity;
}
