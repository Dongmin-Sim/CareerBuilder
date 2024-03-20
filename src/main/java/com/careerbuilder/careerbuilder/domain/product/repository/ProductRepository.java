package com.careerbuilder.careerbuilder.domain.product.repository;

import com.careerbuilder.careerbuilder.domain.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
