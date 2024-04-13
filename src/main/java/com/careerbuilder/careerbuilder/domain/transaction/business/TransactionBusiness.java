package com.careerbuilder.careerbuilder.domain.transaction.business;

import com.careerbuilder.careerbuilder.domain.product.service.ProductService;
import com.careerbuilder.careerbuilder.domain.transaction.business.transactionTypeAction.TypeTransaction;
import com.careerbuilder.careerbuilder.domain.transaction.business.transactionTypeAction.TypeTransactionFactory;
import com.careerbuilder.careerbuilder.domain.transaction.converter.TransactionConverter;
import com.careerbuilder.careerbuilder.domain.transaction.dto.TransactionDetailResponse;
import com.careerbuilder.careerbuilder.domain.transaction.dto.TransactionRegisterRequest;
import com.careerbuilder.careerbuilder.domain.transaction.dto.TransactionResponse;
import com.careerbuilder.careerbuilder.domain.transaction.service.TransactionService;
import com.careerbuilder.careerbuilder.domain.transactionproduct.business.TransactionProductBusiness;
import com.careerbuilder.careerbuilder.global.common.annotation.Business;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Business
@RequiredArgsConstructor
public class TransactionBusiness {

    private final TransactionService transactionService;
    private final TransactionConverter transactionConverter;

    private final TransactionProductBusiness transactionProductBusiness;

    private final TypeTransactionFactory typeTransactionFactory;

    private final ProductService productService;

    /**
     * 거래 타입별 거래 생성 로직
     * <p>
     *     거래에는 입고(IN), 출고(OUT), 조정(ADJUST), 이동(MOVE)의 여러 종류가 있음.
     *     거래 종류별로 필요로 하는 필드값과 로직이 서로 달라 타입별로 비지니스 로직을 따로 수행하기 위해
     *     별도의 공통 핸들러를 둠.
     * </p>
     * @param request 거래 요청 DTO
     * @return 거래 응답 DTO
     *
     *  @see com.careerbuilder.careerbuilder.domain.transaction.business.transactionTypeAction.InTransaction
     *
     */
    @Transactional
    public TransactionResponse register(TransactionRegisterRequest request) {
        TypeTransaction typeTransaction = typeTransactionFactory.getTransaction(request.getTransactionType());
        return typeTransaction.action(request);
    }

    public List<TransactionDetailResponse> getTransactionList() {
        return null;
    }

    public TransactionDetailResponse getTransactionById(@Positive Long transactionId) {
        return null;
    }

    public TransactionDetailResponse updateTransactionById(Long transactionId) {
        return null;
    }

    public TransactionResponse deleteTransaction(Long transactionId) {
        return null;
    }
}
