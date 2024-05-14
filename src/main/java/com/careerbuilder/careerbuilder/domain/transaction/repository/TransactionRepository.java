package com.careerbuilder.careerbuilder.domain.transaction.repository;


import com.careerbuilder.careerbuilder.domain.transaction.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
