package com.careerbuilder.careerbuilder.domain.transaction.controller;

import com.careerbuilder.careerbuilder.domain.transaction.business.TransactionBusiness;
import com.careerbuilder.careerbuilder.domain.transaction.dto.TransactionDetailResponse;
import com.careerbuilder.careerbuilder.domain.transaction.dto.TransactionRegisterRequest;
import com.careerbuilder.careerbuilder.domain.transaction.dto.TransactionResponse;
import com.careerbuilder.careerbuilder.global.common.api.Api;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionApiController {

    private final TransactionBusiness transactionBusiness;

    @PostMapping
    public Api<TransactionResponse> register(
            @RequestBody TransactionRegisterRequest request
    ) {
        var response = transactionBusiness.register(request);
        return Api.OK(response);
    }

    @GetMapping
    public Api<List<TransactionDetailResponse>> getTransactionList() {
        var response = transactionBusiness.getTransactionList();
        return Api.OK(response);
    }

    @GetMapping("/{tx_id}")
    public Api<TransactionDetailResponse> getTransactionById(
            @PathVariable("tx_id") @Positive Long transactionId
    ) {
        var response = transactionBusiness.getTransactionById(transactionId);
        return Api.OK(response);
    }

    @PutMapping("/{tx_id}")
    public Api<TransactionDetailResponse> updateTransactionById(
            @PathVariable("tx_id") @Positive Long transactionId
    ) {
        var response = transactionBusiness.updateTransactionById(transactionId);
        return Api.OK(response);
    }

    @DeleteMapping("/{tx_id}")
    public Api<TransactionResponse> deleteTransaction(
            @PathVariable("tx_id") @Positive Long transactionId
    ) {
        var response = transactionBusiness.deleteTransaction(transactionId);
        return Api.OK(response);
    }
}
