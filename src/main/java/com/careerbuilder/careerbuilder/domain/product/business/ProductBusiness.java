package com.careerbuilder.careerbuilder.domain.product.business;

import com.careerbuilder.careerbuilder.domain.product.converter.ProductConverter;
import com.careerbuilder.careerbuilder.domain.product.dto.*;
import com.careerbuilder.careerbuilder.domain.product.entity.Product;
import com.careerbuilder.careerbuilder.domain.product.service.ProductService;
import com.careerbuilder.careerbuilder.domain.productattribution.converter.ProductAttributionConverter;
import com.careerbuilder.careerbuilder.domain.productattribution.dto.ProductAttributionResponse;
import com.careerbuilder.careerbuilder.domain.productattribution.entity.ProductAttribution;
import com.careerbuilder.careerbuilder.domain.productattribution.service.ProductAttributionService;
import com.careerbuilder.careerbuilder.global.common.annotation.Business;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Business
@RequiredArgsConstructor
public class ProductBusiness {

    private final ProductService productService;
    private final ProductConverter productConverter;

    private final ProductAttributionService productAttributionService;
    private final ProductAttributionConverter productAttributionConverter;

    public ProductResponse register(
            RegisterProductRequest request
    ) {
        Product product = productConverter.toEntity(request);
        Product registered = productService.register(product);
        return productConverter.toResponse(registered);
    }


    public List<ProductResponse> getProductList() {
        List<Product> productList = productService.getProductList();
        return productList.stream()
                .map(productConverter::toResponse)
                .collect(Collectors.toList());
    }

    public ProductDetailResponse getProductById(
            Long productId
    ) {
        Product product = productService.getProductById(productId);

        List<ProductAttributionResponse> productAttributionResponseList = getProductAttributionResponses(product.getId());

        return ProductDetailResponse.builder()
                .productResponse(productConverter.toResponse(product))
                .attributionResponseList(productAttributionResponseList)
                .build();
    }

    @Transactional
    public ProductDetailResponse updateProductById(Long productId, UpdateProductRequest request) {
        Product product = productService.getProductById(productId);

        product.updateProduct(request);

        List<ProductAttributionResponse> productAttributionResponseList = getProductAttributionResponses(product.getId());

        return ProductDetailResponse.builder()
                .productResponse(productConverter.toResponse(product))
                .attributionResponseList(productAttributionResponseList)
                .build();
    }

    @Transactional
    public ProductDetailResponse partialUpdateProductById(Long productId, PartialUpdateProductRequest request) {
        Product product = productService.getProductById(productId);

        // request
        product.partialUpdateProduct(request);

        List<ProductAttributionResponse> productAttributionResponseList = getProductAttributionResponses(product.getId());

        return ProductDetailResponse.builder()
                .productResponse(productConverter.toResponse(product))
                .attributionResponseList(productAttributionResponseList)
                .build();
    }

    private List<ProductAttributionResponse> getProductAttributionResponses(Long productId) {
        List<ProductAttribution> productAttributionList = productAttributionService.getProductAttributionByProductId(productId);
        return productAttributionList.stream()
                .map(productAttributionConverter::toResponse)
                .toList();
    }

    public void deleteProductById(Long productId) {
        productService.deleteProductById(productId);
    }
}
