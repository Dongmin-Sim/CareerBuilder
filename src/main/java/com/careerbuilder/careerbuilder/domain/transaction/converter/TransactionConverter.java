package com.careerbuilder.careerbuilder.domain.transaction.converter;

import com.careerbuilder.careerbuilder.domain.transaction.dto.TransactionRegisterRequest;
import com.careerbuilder.careerbuilder.domain.transaction.dto.TransactionResponse;
import com.careerbuilder.careerbuilder.domain.transaction.entity.Transaction;
import com.careerbuilder.careerbuilder.domain.transaction.entity.type.TransactionStatusType;
import com.careerbuilder.careerbuilder.global.common.annotation.Converter;
import com.careerbuilder.careerbuilder.global.common.error.ErrorCode;
import com.careerbuilder.careerbuilder.global.common.exception.ApiException;

import java.util.Optional;

@Converter
public class TransactionConverter {


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
                            .status(TransactionStatusType.ACTIVE)
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

}
