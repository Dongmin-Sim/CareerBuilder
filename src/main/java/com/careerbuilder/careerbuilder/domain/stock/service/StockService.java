package com.careerbuilder.careerbuilder.domain.stock.service;

import com.careerbuilder.careerbuilder.domain.stock.entity.Stock;
import com.careerbuilder.careerbuilder.domain.stock.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;

    public List<Stock> saveAll(List<Stock> stockList) {
        return stockRepository.saveAll(stockList);
    }

    public Stock findByLocationIdAndProductId(Long locationId, Long productId) {
        return stockRepository.findFirstByLocationIdAndProductIdOrderById(locationId, productId);
    }

    public int updateStockQuantity(Long locationId, Long productId, int quantity) {
        return stockRepository.updateStockQuantityIncreaseByLocationIdAnAndProductId(locationId, productId, quantity);
    }

    public int decreaseStockQuantity(Long locationId, Long productId, int quantity) {
        return stockRepository.updateStockQuantityDecreaseByLocationIdAnAndProductId(locationId, productId, quantity);
    }

    @Transactional
    public int moveStockQuantity(Long fromLocationId, Long toLocationId, Long productId, int quantity) {
        stockRepository.updateStockQuantityDecreaseByLocationIdAnAndProductId(fromLocationId, productId, quantity);
        return stockRepository.updateStockQuantityByLocationIdAnAndProductId(toLocationId, productId, quantity);
    }

    public int adjustStockQuantity(Long toLocationId, Long productId, Integer quantity) {
        return stockRepository.updateStockQuantityByLocationIdAnAndProductId(toLocationId, productId, quantity);
    }

    public boolean canDecrease(Long locationId, Long productId, Integer quantity) {
        Stock stock = findByLocationIdAndProductId(locationId, productId);
        return stock.getStockQuantity() - quantity >= 0;
    }
}
