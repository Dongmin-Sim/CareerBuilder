package com.careerbuilder.careerbuilder.domain.stock.business;

import com.careerbuilder.careerbuilder.domain.location.business.LocationBusiness;
import com.careerbuilder.careerbuilder.domain.location.dto.LocationResponse;
import com.careerbuilder.careerbuilder.domain.product.business.dto.ProductResponseDto;
import com.careerbuilder.careerbuilder.domain.product.db.entity.Product;
import com.careerbuilder.careerbuilder.domain.product.service.ProductService;
import com.careerbuilder.careerbuilder.domain.stock.converter.StockConverter;
import com.careerbuilder.careerbuilder.domain.stock.dto.StockDetailResponse;
import com.careerbuilder.careerbuilder.domain.stock.dto.StockQuantity;
import com.careerbuilder.careerbuilder.domain.stock.dto.StockRequest;
import com.careerbuilder.careerbuilder.domain.stock.dto.StockResponse;
import com.careerbuilder.careerbuilder.domain.stock.entity.Stock;
import com.careerbuilder.careerbuilder.domain.stock.service.StockService;
import com.careerbuilder.careerbuilder.global.common.annotation.Business;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Business
@RequiredArgsConstructor
public class StockBusiness {

    private final StockService stockService;
    private final StockConverter stockConverter;

    private final ProductService productService;
    private final LocationBusiness locationBusiness;

    @Transactional
    public StockResponse registerStock(StockRequest request) {
        // stock 으로 만들기
        Stock entity = stockConverter.toEntity(request);
        Stock stock = stockService.saveStock(entity);
        return stockConverter.toResponse(stock);
    }

    public StockResponse getStockByLocationAndProduct(StockRequest getStockRequest) {
        Stock stock = stockService.findStockByLocationAndProduct(
                getStockRequest.getLocationId(),
                getStockRequest.getProductId()
        );
        return stockConverter.toResponse(stock);
    }

    public List<StockQuantity> getStockTotalQuantityListByLocation() {
        return stockService.findStocksGroupByLocation().stream()
                .map(stockQuantityIfs -> {
                    Long locationId = stockQuantityIfs.getLocationId();
                    LocationResponse location = locationBusiness.getLocationById(locationId);

                    return StockQuantity.builder()
                            .locationId(locationId)
                            .locationName(location.getName())
                            .locationMemo(location.getMemo())
                            .totalQuantity(stockQuantityIfs.getTotalQuantity())
                            .build();
                }).toList();
    }

    public StockDetailResponse getStockListByLocationId(Long locationId) {
        // Location Id 를 가지는 모든 stock 을 조회
        List<Stock> allStockByLocationId = stockService.findStocksByLocation(locationId);

        // LocationId로 Location 가져오기
        LocationResponse location = locationBusiness.getLocationById(locationId);

        // map<product, quantity (sum)>으로 만들어서 리턴
        Map<ProductResponseDto.ProductDto, Integer> productIntegerMap = new HashMap<>();
        for (Stock stock : allStockByLocationId) {
            Product product = productService.getProductById(stock.getProductId());

            productIntegerMap.put(
                    ProductResponseDto.ProductDto.fromDomain(product),
                    stock.getStockQuantity()
            );
        }

        return StockDetailResponse.builder()
                .location(location)
                .productQuantityMap(productIntegerMap)
                .build();
    }

    @Transactional
    public int increaseStock(StockRequest request) {
        Stock entity = stockConverter.toEntity(request);
        return stockService.increaseStockQuantity(entity);
    }

    @Transactional
    public int decreaseStock(StockRequest request) {
        Stock entity = stockConverter.toEntity(request);
        return stockService.decreaseStockQuantity(entity);
    }

    @Transactional
    public int moveStock(StockRequest fromRequest, StockRequest toRequest) {
        Stock fromEntity = stockConverter.toEntity(fromRequest);
        Stock toEntity = stockConverter.toEntity(toRequest);

        return stockService.moveStockQuantity(fromEntity, toEntity);
    }

    @Transactional
    public int adjustStock(StockRequest request) {
        Stock entity = stockConverter.toEntity(request);
        return stockService.adjustStockQuantity(entity);
    }


    public boolean canDecreaseStock(Long locationId, Long productId, Integer quantity) {
        return stockService.canDecrease(locationId, productId, quantity);
    }
}
