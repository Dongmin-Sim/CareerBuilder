package com.careerbuilder.careerbuilder.domain.transaction.business.transactionTypeAction;


import com.careerbuilder.careerbuilder.domain.stock.business.StockBusiness;
import com.careerbuilder.careerbuilder.domain.transaction.dto.Item;
import com.careerbuilder.careerbuilder.domain.transaction.entity.Transaction;
import com.careerbuilder.careerbuilder.domain.transaction.entity.type.TransactionType;
import com.careerbuilder.careerbuilder.domain.transactionItem.business.TransactionItemBusiness;
import com.careerbuilder.careerbuilder.domain.transactionItem.dto.TransactionItemRequest;
import com.careerbuilder.careerbuilder.global.common.error.TransactionError;
import com.careerbuilder.careerbuilder.global.common.exception.ApiException;

public abstract class AbstractAction<E, T> implements Action<E, T>{

    protected final TransactionItemBusiness transactionItemBusiness;
    protected final StockBusiness stockBusiness;

    private final TransactionType type;

    public AbstractAction(TransactionItemBusiness transactionItemBusiness, StockBusiness stockBusiness, TransactionType type) {
        this.transactionItemBusiness = transactionItemBusiness;
        this.stockBusiness = stockBusiness;
        this.type = type;
    }

    @Override
    public void registerTransactionItem(E e, T t) {
        if (e instanceof Transaction && t instanceof Item) {
            Transaction transaction = (Transaction) e;
            Item item = (Item) t;

            TransactionItemRequest productRequest = TransactionItemRequest.builder()
                    .transactionId(transaction.getId())
                    .productId(item.getProductId())
                    .quantity(item.getQuantity())
                    .build();
            transactionItemBusiness.register(productRequest);
        } else {
            throw new ApiException(TransactionError.VALIDATION_ERROR);
        }
    }

    @Override
    public boolean canSupport(TransactionType type) {
        return this.type == type;
    }
}
