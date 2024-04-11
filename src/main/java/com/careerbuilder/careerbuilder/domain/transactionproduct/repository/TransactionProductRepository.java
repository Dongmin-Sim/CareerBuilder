package com.careerbuilder.careerbuilder.domain.transactionproduct.repository;

import com.careerbuilder.careerbuilder.domain.transactionproduct.entity.TransactionProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionProductRepository extends JpaRepository<TransactionProduct, Long> {

}
