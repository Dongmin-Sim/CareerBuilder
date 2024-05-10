package com.careerbuilder.careerbuilder.domain.transactionproduct.service;

import com.careerbuilder.careerbuilder.domain.transactionproduct.entity.TransactionProduct;
import com.careerbuilder.careerbuilder.domain.transactionproduct.repository.TransactionProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionProductService {

    private final TransactionProductRepository transactionProductRepository;

    public TransactionProduct save(TransactionProduct transactionProduct) {
        return transactionProductRepository.save(transactionProduct);
    }

    public List<TransactionProduct> findTransactionProductListById(Long transactionId) {
        return transactionProductRepository.findAllByTransactionId(transactionId);
    }
}
