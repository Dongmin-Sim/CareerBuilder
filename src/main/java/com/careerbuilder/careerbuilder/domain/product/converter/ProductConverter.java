package com.careerbuilder.careerbuilder.domain.product.converter;

import com.careerbuilder.careerbuilder.domain.product.dto.ProductResponse;
import com.careerbuilder.careerbuilder.domain.product.dto.RegisterProductRequest;
import com.careerbuilder.careerbuilder.domain.product.entity.Product;
import com.careerbuilder.careerbuilder.global.common.annotation.Converter;
import com.careerbuilder.careerbuilder.global.common.error.ErrorCode;
import com.careerbuilder.careerbuilder.global.common.exception.ApiException;

import java.util.Optional;

@Converter
public class ProductConverter {

    public Product toEntity(
            RegisterProductRequest request
    ) {
        return Optional.ofNullable(request)
            .map(it -> {
                    return Product.builder()
                            .name(request.getName())
                            .barcode(request.getBarcode())
                            .photoUrl(request.getPhotoUrl())
                            .cost(request.getCost())
                            .price(request.getPrice())
                            .build();
                }).orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT_ERROR));
    }

    public ProductResponse toResponse(
            Product productEntity
    ) {
        return Optional.ofNullable(productEntity)
                .map(it -> {
                    return ProductResponse.builder()
                            .id(productEntity.getId())
                            .name(productEntity.getName())
                            .barcode(productEntity.getBarcode())
                            .photoUrl(productEntity.getPhotoUrl())
                            .cost(productEntity.getCost())
                            .price(productEntity.getPrice())
                            .build();
                }).orElseThrow(()->new ApiException(ErrorCode.NULL_POINT_ERROR));
    }
}
