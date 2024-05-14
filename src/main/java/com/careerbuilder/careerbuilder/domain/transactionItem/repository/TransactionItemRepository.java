package com.careerbuilder.careerbuilder.domain.transactionItem.repository;

import com.careerbuilder.careerbuilder.domain.transactionItem.entity.TransactionItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionItemRepository extends JpaRepository<TransactionItem, Long> {

    List<TransactionItem> findAllByTransactionId(Long transactionId);
}
