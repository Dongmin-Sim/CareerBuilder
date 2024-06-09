package com.careerbuilder.careerbuilder.domain.product.service;

import com.careerbuilder.careerbuilder.domain.product.db.entity.ProductAttribution;
import com.careerbuilder.careerbuilder.domain.product.db.repository.ProductAttributionJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductAttributionService {

    private final ProductAttributionJpaRepository productAttributionRepository;

    public List<ProductAttribution> getProductAttributionByProductId(
            Long productId
    ) {
        return productAttributionRepository.findAllByProductIdOrderById(productId);
    }
}
