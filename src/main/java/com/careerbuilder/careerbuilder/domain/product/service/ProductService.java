package com.careerbuilder.careerbuilder.domain.product.service;

import com.careerbuilder.careerbuilder.domain.product.db.entity.Product;
import com.careerbuilder.careerbuilder.domain.product.db.repository.ProductJpaRepository;
import com.careerbuilder.careerbuilder.domain.product.service.error.ProductErrorCode;
import com.careerbuilder.careerbuilder.global.common.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.careerbuilder.careerbuilder.domain.product.service.error.ProductErrorCode.PRODUCT_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductJpaRepository productJpaRepository;

    public Product register(Product productEntity) {
        return productJpaRepository.save(productEntity);
    }

    public List<Product> getProductList() {
        return productJpaRepository.findAll();
    }

    public Product getProductById(Long productId) {
        return productJpaRepository.findById(productId)
                .orElseThrow(() -> new ApiException(PRODUCT_NOT_FOUND));
    }

    public void deleteProductById(Long productId) {
        productJpaRepository.delete(
                productJpaRepository.findById(productId)
                        .orElseThrow(() -> new ApiException(PRODUCT_NOT_FOUND))
        );
    }

    public boolean isExist(Long productId) {
        return productJpaRepository.existsById(productId);
    }

    public List<Product> searchProduct(String keyword, String field) {
        List<String> fieldList = new ArrayList<>(Arrays.asList("name", "barcode", "attribution"));
        if (!fieldList.contains(field)) {
            throw new ApiException(ProductErrorCode.INVALID_SEARCH_FIELD);
        }

        return List.of();
    }
}
