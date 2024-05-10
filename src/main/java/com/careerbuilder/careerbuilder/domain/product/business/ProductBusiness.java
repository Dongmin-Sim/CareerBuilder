package com.careerbuilder.careerbuilder.domain.product.business;

import com.careerbuilder.careerbuilder.domain.product.converter.ProductConverter;
import com.careerbuilder.careerbuilder.domain.product.dto.*;
import com.careerbuilder.careerbuilder.domain.product.entity.Product;
import com.careerbuilder.careerbuilder.domain.product.service.ProductService;
import com.careerbuilder.careerbuilder.domain.productattribution.converter.ProductAttributionConverter;
import com.careerbuilder.careerbuilder.domain.productattribution.dto.ProductAttributionResponse;
import com.careerbuilder.careerbuilder.domain.productattribution.entity.ProductAttribution;
import com.careerbuilder.careerbuilder.domain.productattribution.service.ProductAttributionService;
import com.careerbuilder.careerbuilder.domain.stock.business.StockBusiness;
import com.careerbuilder.careerbuilder.global.common.annotation.Business;
import com.careerbuilder.careerbuilder.global.common.error.ErrorCode;
import com.careerbuilder.careerbuilder.global.common.error.ProductErrorCode;
import com.careerbuilder.careerbuilder.global.common.exception.ApiException;
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

    private final StockBusiness stockBusiness;

    public ProductResponse register(
            RegisterProductRequest request
    ) {
        // 제품명은 null 일 수 없다.
        if (request.getName() == null) {
            throw new ApiException(ErrorCode.NULL_POINT_ERROR);
        }

        // 제품명은 공백일 수 없다.
        if (request.getName().isEmpty() || request.getName().isBlank()) {
            throw new ApiException(ProductErrorCode.REQUEST_INVALIDATION);
        }

        // 초기 수량값은 null일 수 없다.
        if (request.getInitialQuantity() == null) {
            throw new ApiException(ErrorCode.NULL_POINT_ERROR);
        }

        Product product = productConverter.toEntity(request);
        Product registered = productService.register(product);

        // 초기 물량 등록
        Long locationId = request.getLocationId();
        Integer initialQuantity = request.getInitialQuantity();

        StockRequest stockRequest = StockRequest.builder()
                .locationId(locationId)
                .productId(registered.getId())
                .stockQuantity(initialQuantity)
                .build();

        stockBusiness.registerStock(stockRequest);

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

    public ProductResponse getProductById(Long productId) {
        Product product = productService.getProductById(productId);
        return productConverter.toResponse(product);
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
