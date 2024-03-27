package com.careerbuilder.careerbuilder.domain.productattribution.service;

import com.careerbuilder.careerbuilder.domain.productattribution.entity.ProductAttribution;
import com.careerbuilder.careerbuilder.domain.productattribution.repository.ProductAttributionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductAttributionService {

    private final ProductAttributionRepository productAttributionRepository;

    public List<ProductAttribution> getProductAttributionByProductId(
            Long productId
    ) {
        return productAttributionRepository.findAllByProductIdOrderById(productId);
    }
}
