package com.careerbuilder.careerbuilder.domain.transaction.business.transactionTypeAction;

import com.careerbuilder.careerbuilder.domain.transaction.entity.type.TransactionType;

public interface Action<E, T> {

    void registerTransactionItem(E entity, T item);
    void updateStock(E entity, T item);
    void rollbackStock(E entity);
    boolean canSupport(TransactionType type);
}
