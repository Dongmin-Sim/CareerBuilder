package com.careerbuilder.careerbuilder.domain.stock.business;

import com.careerbuilder.careerbuilder.domain.stock.converter.StockConverter;
import com.careerbuilder.careerbuilder.domain.stock.service.StockService;
import com.careerbuilder.careerbuilder.global.common.annotation.Business;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.careerbuilder.careerbuilder.domain.transaction.dto.TransactionRegisterRequest.Item;

@Business
@RequiredArgsConstructor
public class StockBusiness {

    private final StockService stockService;

    @Transactional
    public void increaseStockWithItemList(
            Long locationId, List<Item> items
    ) {
        for (Item item : items) {
            stockService.updateStockQuantity(
                    locationId,
                    item.getProductId(),
                    item.getQuantity()
            );
        }
    }

    @Transactional
    public void decreaseStockWithItemList(
            Long locationId, List<Item> items
    ) {
        for (Item item : items) {
            stockService.decreaseStockQuantity(
                    locationId,
                    item.getProductId(),
                    item.getQuantity()
            );
        }
    }

    @Transactional
    public void moveStockWithItemList(Long fromLocation, Long toLocation, List<Item> items) {
        items.forEach(item -> {
            stockService.moveStockQuantity(fromLocation, toLocation, item.getProductId(), item.getQuantity());
        });
    }

    public boolean canDecreaseStock(Long locationId, Long productId, Integer quantity) {
        return stockService.canDecrease(locationId, productId, quantity);
    }
}
