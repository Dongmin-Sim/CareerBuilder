package com.careerbuilder.careerbuilder.domain.transactionproduct.converter;

import com.careerbuilder.careerbuilder.domain.transactionproduct.dto.TransactionProductRequest;
import com.careerbuilder.careerbuilder.domain.transactionproduct.dto.TransactionProductResponse;
import com.careerbuilder.careerbuilder.domain.transactionproduct.entity.TransactionProduct;
import com.careerbuilder.careerbuilder.global.common.annotation.Converter;
import com.careerbuilder.careerbuilder.global.common.error.ErrorCode;
import com.careerbuilder.careerbuilder.global.common.exception.ApiException;

import java.util.Optional;

@Converter
public class TransactionProductConverter {
    public TransactionProduct toEntity(TransactionProductRequest request) {
        return Optional.ofNullable(request)
                .map(it->{
                    return TransactionProduct.builder()
                            .transactionId(request.getTransactionId())
                            .productId(request.getProductId())
                            .quantity(request.getQuantity())
                            .build();
                }).orElseThrow(()->new ApiException(ErrorCode.NULL_POINT_ERROR));
    }

    public TransactionProductResponse toResponse(TransactionProduct entity) {
        return Optional.ofNullable(entity)
                .map(it -> {
                    return TransactionProductResponse.builder()
                            .id(entity.getId())
                            .transactionId(entity.getTransactionId())
                            .productId(entity.getProductId())
                            .quantity(entity.getQuantity())
                            .build();
                }).orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT_ERROR));
    }
}
