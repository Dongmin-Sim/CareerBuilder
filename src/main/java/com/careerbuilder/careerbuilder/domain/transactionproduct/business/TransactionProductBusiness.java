package com.careerbuilder.careerbuilder.domain.transactionproduct.business;

import com.careerbuilder.careerbuilder.domain.transactionproduct.converter.TransactionProductConverter;
import com.careerbuilder.careerbuilder.domain.transactionproduct.dto.TransactionProductRequest;
import com.careerbuilder.careerbuilder.domain.transactionproduct.dto.TransactionProductResponse;
import com.careerbuilder.careerbuilder.domain.transactionproduct.entity.TransactionProduct;
import com.careerbuilder.careerbuilder.domain.transactionproduct.service.TransactionProductService;
import com.careerbuilder.careerbuilder.global.common.annotation.Business;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Business
@RequiredArgsConstructor
public class TransactionProductBusiness {

    private final TransactionProductService transactionProductService;
    private final TransactionProductConverter transactionProductConverter;

    @Transactional
    public TransactionProductResponse register(
            TransactionProductRequest request
    ) {
        TransactionProduct transactionProduct = transactionProductConverter.toEntity(request);
        TransactionProduct save = transactionProductService.save(transactionProduct);
        return transactionProductConverter.toResponse(save);
    }

    public List<TransactionProductResponse> getTransactionProductList(Long transactionId) {
        List<TransactionProduct> transactionProductList = transactionProductService.findTransactionProductListById(transactionId);
        return transactionProductList.stream()
                .map(transactionProduct -> {
                    return transactionProductConverter.toResponse(transactionProduct);
                }).toList();
    }
}
