package com.careerbuilder.careerbuilder.domain.transactionItem.converter;

import com.careerbuilder.careerbuilder.domain.transactionItem.dto.TransactionItemRequest;
import com.careerbuilder.careerbuilder.domain.transactionItem.dto.TransactionItemResponse;
import com.careerbuilder.careerbuilder.domain.transactionItem.entity.TransactionItem;
import com.careerbuilder.careerbuilder.global.common.annotation.Converter;
import com.careerbuilder.careerbuilder.global.common.error.ErrorCode;
import com.careerbuilder.careerbuilder.global.common.exception.ApiException;

import java.util.Optional;

@Converter
public class TransactionItemConverter {
    public TransactionItem toEntity(TransactionItemRequest request) {
        return Optional.ofNullable(request)
                .map(it->{
                    return TransactionItem.builder()
                            .transactionId(request.getTransactionId())
                            .productId(request.getProductId())
                            .quantity(request.getQuantity())
                            .build();
                }).orElseThrow(()->new ApiException(ErrorCode.NULL_POINT_ERROR));
    }

    public TransactionItemResponse toResponse(TransactionItem entity) {
        return Optional.ofNullable(entity)
                .map(it -> {
                    return TransactionItemResponse.builder()
                            .id(entity.getId())
                            .transactionId(entity.getTransactionId())
                            .productId(entity.getProductId())
                            .quantity(entity.getQuantity())
                            .build();
                }).orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT_ERROR));
    }
}
