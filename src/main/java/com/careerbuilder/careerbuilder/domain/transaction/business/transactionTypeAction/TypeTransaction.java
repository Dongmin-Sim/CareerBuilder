package com.careerbuilder.careerbuilder.domain.transaction.business.transactionTypeAction;

import com.careerbuilder.careerbuilder.domain.transaction.dto.TransactionRegisterRequest;
import com.careerbuilder.careerbuilder.domain.transaction.dto.TransactionResponse;
import com.careerbuilder.careerbuilder.domain.transaction.entity.type.TransactionType;

public interface TypeTransaction {

    /**
     * 거래 유형에 따른 비지니스 로직을 실행
     * @param request
     * @return
     */
    TransactionResponse action(TransactionRegisterRequest request);

    /**
     * 거래 유형에 따른 요청 정보 검증
     * @param request
     * @return
     */
    void validateRequest(TransactionRegisterRequest request);

    TransactionType getType();
}
