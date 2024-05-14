package com.careerbuilder.careerbuilder.domain.transaction.service;

import com.careerbuilder.careerbuilder.domain.transaction.entity.Transaction;
import com.careerbuilder.careerbuilder.domain.transaction.repository.TransactionRepository;
import com.careerbuilder.careerbuilder.global.common.error.ErrorCode;
import com.careerbuilder.careerbuilder.global.common.exception.ApiException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;

    @Transactional
    public Transaction save(Transaction transactionEntity) {
        return transactionRepository.save(transactionEntity);
    }

    public Transaction findById(Long transactionId) {
        return transactionRepository.findById(transactionId)
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT_ERROR));
    }

    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

}
