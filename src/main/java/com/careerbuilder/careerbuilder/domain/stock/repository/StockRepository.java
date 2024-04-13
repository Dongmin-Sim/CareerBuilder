package com.careerbuilder.careerbuilder.domain.stock.repository;

import com.careerbuilder.careerbuilder.domain.stock.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

}
