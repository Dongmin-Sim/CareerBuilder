package com.careerbuilder.careerbuilder.domain.transaction.business.transactionTypeAction;

import com.careerbuilder.careerbuilder.domain.transaction.entity.type.TransactionType;
import com.careerbuilder.careerbuilder.global.common.error.TransactionError;
import com.careerbuilder.careerbuilder.global.common.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TypeTransactionFactory {
    private final List<TypeTransaction> actions;

    public TypeTransactionFactory(List<TypeTransaction> actions) {
        this.actions = actions;
    }

    public TypeTransaction getTransaction(TransactionType type) {
        return this.actions.stream()
                .filter(action -> type.equals(action.getType()))
                .findFirst()
                .orElseThrow(() -> new ApiException(TransactionError.TRANSACTION_TYPE_ERROR));
    }
}
