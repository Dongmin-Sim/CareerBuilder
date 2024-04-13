package com.careerbuilder.careerbuilder.domain.transaction.service;

import com.careerbuilder.careerbuilder.domain.transaction.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;



}
