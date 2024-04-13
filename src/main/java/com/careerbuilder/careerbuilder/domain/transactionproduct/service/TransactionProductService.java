package com.careerbuilder.careerbuilder.domain.transactionproduct.service;

import com.careerbuilder.careerbuilder.domain.transactionproduct.repository.TransactionProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionProductService {

    private final TransactionProductRepository transactionProductRepository;


}
