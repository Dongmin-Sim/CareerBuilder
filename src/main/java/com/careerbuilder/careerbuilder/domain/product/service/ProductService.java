package com.careerbuilder.careerbuilder.domain.product.service;

import com.careerbuilder.careerbuilder.domain.product.entity.Product;
import com.careerbuilder.careerbuilder.domain.product.repository.ProductRepository;
import com.careerbuilder.careerbuilder.global.common.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static com.careerbuilder.careerbuilder.global.common.error.ProductErrorCode.PRODUCT_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product register(Product productEntity) {
        return productRepository.save(productEntity);
    }

    public List<Product> getProductList() {
        return productRepository.findAll();
    }

    public Product getProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ApiException(PRODUCT_NOT_FOUND));
    }

    public void deleteProductById(Long productId) {
        productRepository.delete(
                productRepository.findById(productId)
                        .orElseThrow(() -> new ApiException(PRODUCT_NOT_FOUND))
        );
    }

    public boolean isExist(Long productId) {
        return productRepository.existsById(productId);
    }
}
