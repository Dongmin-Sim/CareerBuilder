package com.careerbuilder.careerbuilder.domain.product.db.repository;

import com.careerbuilder.careerbuilder.domain.product.db.entity.ProductAttribution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductAttributionJpaRepository extends JpaRepository<ProductAttribution, Long> {

    // select * from product_attribution where productId = ? order by id;
    List<ProductAttribution> findAllByProductIdOrderById(Long productId);
}
