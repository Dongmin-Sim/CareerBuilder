package com.careerbuilder.careerbuilder.domain.stock.service;

import com.careerbuilder.careerbuilder.domain.stock.entity.Stock;
import com.careerbuilder.careerbuilder.domain.stock.repository.StockRepository;
import com.careerbuilder.careerbuilder.domain.transaction.entity.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.PublicKey;
import java.util.List;
import java.util.Map;

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
        return stockRepository.updateStockQuantityByLocationIdAnAndProductId(locationId, productId, quantity);
    }

    public int decreaseStockQuantity(Long locationId, Long productId, int quantity) {
        return stockRepository.updateStockQuantityDecreaseByLocationIdAnAndProductId(locationId, productId, quantity);
    }


    public boolean canDecrease(Long locationId, Long productId, Integer quantity) {
        Stock stock = findByLocationIdAndProductId(locationId, productId);
        return stock.getStockQuantity() - quantity >= 0;
    }
}
