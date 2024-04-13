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
}
