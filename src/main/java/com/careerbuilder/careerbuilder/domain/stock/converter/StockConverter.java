package com.careerbuilder.careerbuilder.domain.stock.converter;

import com.careerbuilder.careerbuilder.domain.stock.dto.StockRequest;
import com.careerbuilder.careerbuilder.domain.stock.entity.Stock;
import com.careerbuilder.careerbuilder.global.common.annotation.Converter;
import com.careerbuilder.careerbuilder.global.common.error.ErrorCode;
import com.careerbuilder.careerbuilder.global.common.exception.ApiException;

import java.util.Optional;

@Converter
public class StockConverter {

    public Stock toEntity(StockRequest request) {
        return Optional.ofNullable(request)
                .map(it -> {
                    return Stock.builder()
                            .locationId(request.getLocationId())
                            .productId(request.getProductId())
                            .stockQuantity(request.getStockQuantity())
                            .build();
                }).orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT_ERROR));
    }
}
