package com.careerbuilder.careerbuilder.domain.stock.service;

import com.careerbuilder.careerbuilder.domain.stock.dto.StockQuantityIfs;
import com.careerbuilder.careerbuilder.domain.stock.entity.Stock;
import com.careerbuilder.careerbuilder.domain.stock.repository.StockRepository;
import com.careerbuilder.careerbuilder.global.common.error.ErrorCode;
import com.careerbuilder.careerbuilder.global.common.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;

    public Stock saveStock(Stock entity) {
        return stockRepository.save(entity);
    }

    public List<Stock> saveStocks(List<Stock> stockList) {
        return stockRepository.saveAll(stockList);
    }

    public List<StockQuantityIfs> findStocksGroupByLocation() {
        return stockRepository.findStockTotalQuantityGroupByLocation();
    }

    public List<Stock> findStocksByLocation(Long locationId) {
        return stockRepository.findAllByLocationIdOrderById(locationId);
    }

    public Stock findStockByLocationAndProduct(Long locationId, Long productId) {
        return stockRepository.findFirstByLocationIdAndProductIdOrderById(locationId, productId)
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT_ERROR));
    }

    public int increaseStockQuantity(Stock increaseStock) {
        return stockRepository.increaseStockQuantityByLocationIdAnAndProductId(
                increaseStock.getLocationId(),
                increaseStock.getProductId(),
                increaseStock.getStockQuantity()
        );
    }

    public int decreaseStockQuantity(Stock decreaseStock) {
        return stockRepository.decreaseStockQuantityByLocationIdAnAndProductId(
                decreaseStock.getLocationId(),
                decreaseStock.getProductId(),
                decreaseStock.getStockQuantity()
        );
    }

    public int moveStockQuantity(Stock fromStock, Stock toStock) {
        stockRepository.decreaseStockQuantityByLocationIdAnAndProductId(
                fromStock.getLocationId(),
                fromStock.getProductId(),
                fromStock.getStockQuantity()
        );
        return stockRepository.increaseStockQuantityByLocationIdAnAndProductId(
                toStock.getLocationId(),
                toStock.getProductId(),
                toStock.getStockQuantity()
        );
    }

    public int adjustStockQuantity(Stock adjustStock) {
        return stockRepository.updateStockQuantityByLocationIdAnAndProductId(
                adjustStock.getLocationId(),
                adjustStock.getProductId(),
                adjustStock.getStockQuantity()
        );
    }

    public boolean canDecrease(Long locationId, Long productId, Integer quantity) {
        Stock stock = findStockByLocationAndProduct(locationId, productId);
        return Optional.ofNullable(stock)
                .map(entity -> {
                    return entity.getStockQuantity() - quantity >= 0;
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT_ERROR));
    }
}
