package com.careerbuilder.careerbuilder.domain.transaction.business;

import com.careerbuilder.careerbuilder.domain.transaction.dto.TransactionRegisterRequest;
import com.careerbuilder.careerbuilder.domain.transaction.dto.TransactionResponse;
import com.careerbuilder.careerbuilder.domain.transaction.service.TransactionService;
import com.careerbuilder.careerbuilder.global.common.annotation.Business;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Business
@RequiredArgsConstructor
public class TransactionBusiness {

    private final TransactionService transactionService;

    public TransactionResponse register(TransactionRegisterRequest request) {
        return null;
    }

    public List<TransactionResponse> getTransactionList() {
        return null;
    }

    public TransactionResponse getTransactionById(@Positive Long transactionId) {
        return null;
    }

    public TransactionResponse updateTransactionById(Long transactionId) {
        return null;
    }

    public TransactionResponse deleteTransaction(Long transactionId) {
        return null;
    }
}
