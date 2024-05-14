package com.careerbuilder.careerbuilder.domain.transaction.business.transactionTypeAction;

import com.careerbuilder.careerbuilder.domain.stock.business.StockBusiness;
import com.careerbuilder.careerbuilder.domain.stock.dto.StockRequest;
import com.careerbuilder.careerbuilder.domain.transaction.dto.Item;
import com.careerbuilder.careerbuilder.domain.transaction.entity.Transaction;
import com.careerbuilder.careerbuilder.domain.transaction.entity.type.TransactionType;
import com.careerbuilder.careerbuilder.domain.transactionItem.business.TransactionItemBusiness;
import org.springframework.stereotype.Component;

@Component
public class OutAction extends AbstractAction<Transaction, Item> {

    public OutAction(TransactionItemBusiness transactionItemBusiness, StockBusiness stockBusiness) {
        super(transactionItemBusiness, stockBusiness, TransactionType.OUT);
    }

    @Override
    public void updateStock(Transaction entity, Item item) {
        StockRequest stockRequest = StockRequest.builder()
                .locationId(entity.getToLocationId())
                .productId(item.getProductId())
                .stockQuantity(item.getQuantity())
                .build();
        stockBusiness.decreaseStock(stockRequest);
    }

    @Override
    public void rollbackStock(Transaction entity) {
        transactionItemBusiness.getTransactionItemList(entity.getId())
                .forEach(txItem -> {
                    StockRequest stockRequest = StockRequest.builder()
                            .locationId(entity.getFromLocationId())
                            .productId(txItem.getProductId())
                            .stockQuantity(txItem.getQuantity())
                            .build();
                    stockBusiness.increaseStock(stockRequest);
                });
    }
}
