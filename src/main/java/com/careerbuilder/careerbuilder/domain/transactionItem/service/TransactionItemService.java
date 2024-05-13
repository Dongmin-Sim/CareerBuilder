package com.careerbuilder.careerbuilder.domain.transactionItem.service;

import com.careerbuilder.careerbuilder.domain.transactionItem.entity.TransactionItem;
import com.careerbuilder.careerbuilder.domain.transactionItem.repository.TransactionItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionItemService {

    private final TransactionItemRepository transactionItemRepository;

    public TransactionItem save(TransactionItem transactionItem) {
        return transactionItemRepository.save(transactionItem);
    }

    public List<TransactionItem> findTransactionItemListById(Long transactionId) {
        return transactionItemRepository.findAllByTransactionId(transactionId);
    }
}
