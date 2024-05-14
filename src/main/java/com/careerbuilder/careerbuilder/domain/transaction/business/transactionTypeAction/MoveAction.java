package com.careerbuilder.careerbuilder.domain.transaction.business.transactionTypeAction;

import com.careerbuilder.careerbuilder.domain.stock.business.StockBusiness;
import com.careerbuilder.careerbuilder.domain.stock.dto.StockRequest;
import com.careerbuilder.careerbuilder.domain.transaction.dto.Item;
import com.careerbuilder.careerbuilder.domain.transaction.entity.Transaction;
import com.careerbuilder.careerbuilder.domain.transaction.entity.type.TransactionType;
import com.careerbuilder.careerbuilder.domain.transactionItem.business.TransactionItemBusiness;
import org.springframework.stereotype.Component;

@Component
public class MoveAction extends AbstractAction<Transaction, Item> {


    public MoveAction(TransactionItemBusiness transactionItemBusiness, StockBusiness stockBusiness) {
        super(transactionItemBusiness, stockBusiness, TransactionType.MOVE);
    }

    @Override
    public void updateStock(Transaction entity, Item item) {
        StockRequest fromRequest = StockRequest.builder()
                .locationId(entity.getFromLocationId())
                .productId(item.getProductId())
                .stockQuantity(item.getQuantity())
                .build();

        StockRequest toRequest = StockRequest.builder()
                .locationId(entity.getToLocationId())
                .productId(item.getProductId())
                .stockQuantity(item.getQuantity())
                .build();
        stockBusiness.moveStock(fromRequest, toRequest);
    }

    @Override
    public void rollbackStock(Transaction entity) {
        transactionItemBusiness.getTransactionItemList(entity.getId())
                .forEach(txItem -> {
                    StockRequest fromRequest = StockRequest.builder()
                            .locationId(entity.getFromLocationId())
                            .productId(txItem.getProductId())
                            .stockQuantity(txItem.getQuantity())
                            .build();

                    StockRequest toRequest = StockRequest.builder()
                            .locationId(entity.getToLocationId())
                            .productId(txItem.getProductId())
                            .stockQuantity(txItem.getQuantity())
                            .build();

                    stockBusiness.moveStock(toRequest, fromRequest);
                });
    }
}
