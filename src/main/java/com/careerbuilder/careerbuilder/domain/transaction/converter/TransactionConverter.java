package com.careerbuilder.careerbuilder.domain.transaction.converter;

import com.careerbuilder.careerbuilder.domain.transaction.dto.TransactionDetailResponse;
import com.careerbuilder.careerbuilder.domain.transaction.dto.TransactionRegisterRequest;
import com.careerbuilder.careerbuilder.domain.transaction.dto.TransactionResponse;
import com.careerbuilder.careerbuilder.domain.transaction.dto.TransactionUpdateRequest;
import com.careerbuilder.careerbuilder.domain.transaction.entity.Transaction;
import com.careerbuilder.careerbuilder.domain.transaction.entity.type.TransactionStatus;
import com.careerbuilder.careerbuilder.domain.transaction.entity.type.TransactionType;
import com.careerbuilder.careerbuilder.global.common.annotation.Converter;
import com.careerbuilder.careerbuilder.global.common.error.ErrorCode;
import com.careerbuilder.careerbuilder.global.common.exception.ApiException;

import java.util.Optional;

@Converter
public class TransactionConverter {

    public Transaction toUpdateEntity(
            Long originTransactionId,
            TransactionType transactionType,
            TransactionUpdateRequest updateRequest
    ) {
        return Optional.ofNullable(updateRequest)
                .map(it -> {
                    return Transaction.builder()
                            .originTransactionId(originTransactionId)
                            .transactionType(transactionType)
                            .fromLocationId(updateRequest.getFromLocation())
                            .toLocationId(updateRequest.getToLocation())
                            .partnerId(updateRequest.getPartner())
                            .memo(updateRequest.getMemo())
                            .status(TransactionStatus.ACTIVE)
                            .build();
                }).orElseThrow(()->new ApiException(ErrorCode.NULL_POINT_ERROR));
    }

    public Transaction toEntity(
            TransactionRegisterRequest registerRequest
    ){
        return Optional.ofNullable(registerRequest)
                .map(it->{
                    return Transaction.builder()
                            .transactionType(registerRequest.getTransactionType())
                            .fromLocationId(registerRequest.getFromLocation())
                            .toLocationId(registerRequest.getToLocation())
                            .partnerId(registerRequest.getPartner())
                            .memo(registerRequest.getMemo())
                            .status(TransactionStatus.ACTIVE)
                            .build();
                }).orElseThrow(()->new ApiException(ErrorCode.NULL_POINT_ERROR));

    }

    public TransactionResponse toResponse(
            Transaction transaction
    ) {
        return TransactionResponse.builder()
                .id(transaction.getId())
                .transactionType(transaction.getTransactionType())
                .build();
    }

    public TransactionDetailResponse toDetailResponse(
            Transaction transaction
    ) {
        return TransactionDetailResponse.builder()
                .id(transaction.getId())
                .transactionType(transaction.getTransactionType())
                .fromLocation(transaction.getFromLocationId())
                .toLocation(transaction.getToLocationId())
                .partner(transaction.getPartnerId())
                .memo(transaction.getMemo())
                .status(transaction.getStatus())
                .createdAt(transaction.getCreatedAt())
                .updatedAt(transaction.getUpdatedAt())
                .build();
    }
}
