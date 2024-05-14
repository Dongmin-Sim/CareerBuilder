package com.careerbuilder.careerbuilder.domain.transaction.business.transactionTypeAction;

import com.careerbuilder.careerbuilder.domain.transaction.entity.type.TransactionType;
import com.careerbuilder.careerbuilder.global.common.error.TransactionError;
import com.careerbuilder.careerbuilder.global.common.exception.ApiException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ActionFactory {
    private final List<Action> actions;

    public ActionFactory(List<Action> actions) {
        this.actions = actions;
    }

    public Action getAction(TransactionType type) {
        return this.actions.stream()
                .filter(action -> action.canSupport(type))
                .findFirst()
                .orElseThrow(()->new ApiException(TransactionError.TRANSACTION_TYPE_ERROR));
    }
}
