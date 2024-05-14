package com.careerbuilder.careerbuilder.domain.transaction.business.transactionTypeAction;

import com.careerbuilder.careerbuilder.domain.stock.business.StockBusiness;
import com.careerbuilder.careerbuilder.domain.stock.dto.StockRequest;
import com.careerbuilder.careerbuilder.domain.stock.dto.StockResponse;
import com.careerbuilder.careerbuilder.domain.transaction.dto.Item;
import com.careerbuilder.careerbuilder.domain.transaction.entity.Transaction;
import com.careerbuilder.careerbuilder.domain.transaction.entity.type.TransactionType;
import com.careerbuilder.careerbuilder.domain.transactionItem.business.TransactionItemBusiness;
import com.careerbuilder.careerbuilder.domain.transactionItem.dto.TransactionItemRequest;
import org.springframework.stereotype.Component;

@Component
public class AdjustAction extends AbstractAction<Transaction, Item> {

    public AdjustAction(TransactionItemBusiness transactionItemBusiness, StockBusiness stockBusiness) {
        super(transactionItemBusiness, stockBusiness, TransactionType.ADJUST);
    }

    @Override
    public void registerTransactionItem(Transaction entity, Item item) {
        // 현재 재고량
        StockRequest getStockRequest = StockRequest.builder()
                .locationId(entity.getToLocationId())
                .productId(item.getProductId())
                .build();
        StockResponse currentStock = stockBusiness.getStockByLocationAndProduct(getStockRequest);

        // 조정 거래 Item 등록(변화량:재고량 - 조정량)
        TransactionItemRequest productRequest = TransactionItemRequest.builder()
                .transactionId(entity.getId())
                .productId(item.getProductId())
                .quantity(currentStock.getStockQuantity() - item.getQuantity())
                .build();
        transactionItemBusiness.register(productRequest);
    }

    @Override
    public void updateStock(Transaction entity, Item item) {
        StockRequest stockRequest = StockRequest.builder()
                .locationId(entity.getToLocationId())
                .productId(item.getProductId())
                .stockQuantity(item.getQuantity())
                .build();
        stockBusiness.adjustStock(stockRequest);
    }

    @Override
    public void rollbackStock(Transaction entity) {
        transactionItemBusiness.getTransactionItemList(entity.getId())
                .forEach(txItem -> {
                    StockRequest stockRequest = StockRequest.builder()
                            .locationId(entity.getToLocationId())
                            .productId(txItem.getProductId())
                            .stockQuantity(txItem.getQuantity())
                            .build();
                    stockBusiness.increaseStock(stockRequest);
                });
    }
}
