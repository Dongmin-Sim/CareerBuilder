package com.careerbuilder.careerbuilder.domain.transaction.business.transactionTypeAction;

import com.careerbuilder.careerbuilder.domain.location.service.LocationService;
import com.careerbuilder.careerbuilder.domain.product.service.ProductService;
import com.careerbuilder.careerbuilder.domain.stock.business.StockBusiness;
import com.careerbuilder.careerbuilder.domain.transaction.converter.TransactionConverter;
import com.careerbuilder.careerbuilder.domain.transaction.dto.TransactionRegisterRequest;
import com.careerbuilder.careerbuilder.domain.transaction.dto.TransactionResponse;
import com.careerbuilder.careerbuilder.domain.transaction.entity.Transaction;
import com.careerbuilder.careerbuilder.domain.transaction.entity.type.TransactionType;
import com.careerbuilder.careerbuilder.domain.transaction.service.TransactionService;
import com.careerbuilder.careerbuilder.domain.transactionproduct.business.TransactionProductBusiness;
import com.careerbuilder.careerbuilder.global.common.error.TransactionError;
import com.careerbuilder.careerbuilder.global.common.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.careerbuilder.careerbuilder.domain.transaction.dto.TransactionRegisterRequest.*;
import static com.careerbuilder.careerbuilder.domain.transaction.entity.type.TransactionType.*;


@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InTransaction implements TypeTransaction {
    private final TransactionType type = IN;

    private final TransactionProductBusiness transactionProductBusiness;
    private final StockBusiness stockBusiness;

    private final TransactionService transactionService;
    private final ProductService productService;
    private final LocationService locationService;

    private final TransactionConverter transactionConverter;

    /**
     * 입고(IN) 거래 비지니스 로직
     *
     * @param request 거래 요청 DTO
     * @return 거래 응답 DTO
     */
    @Transactional
    @Override
        public TransactionResponse action(TransactionRegisterRequest request) {
        validateRequest(request);

        Transaction txEntity = transactionConverter.toEntity(request);
        Transaction registerTx = transactionService.save(txEntity);

        transactionProductBusiness.registerTransactionProductItemList(
                registerTx.getId(),
                request.getItems()
        );

        stockBusiness.increaseStockWithItemList(
                request.getToLocation(),
                request.getItems()
        );

        return transactionConverter.toResponse(registerTx);
    }

    /**
     * <p>거래 유형에 따른 검증 로직</p>
     * <ul>
     *      <l1> 거래 유형이 in(입고)라면 toLocation 값은 필수이어야 함.  </l1>
     *
     *      <l1> 거래 유형이 in(입고)라면 items 는 반드시 1개 이상이어야 함.  </l1>
     *
     *      <l1> 등록되지 않은 location 에는 입고할 수 없다.</l1>
     *
     *      <l1> 등록되지 않은 product 는 입고할 수 없다.</l1>
     *
     *      <l1> items 의 각 quantity 들은 양수값을 가져야함.</l1>
     * </ul>
     * @param request 거래 요청 DTO
     */
    @Override
    public void validateRequest(TransactionRegisterRequest request) {
        if (request.getToLocation() == null) {
            throw new ApiException(TransactionError.VALIDATION_ERROR);
        }

        if (request.getItems().isEmpty()) {
            throw new ApiException(TransactionError.VALIDATION_ERROR);
        }

        Long toLocation = request.getToLocation();
        if (!locationService.isExist(toLocation)) {
            throw new ApiException(TransactionError.VALIDATION_ERROR);
        }

        List<Item> items = request.getItems();
        for (Item item : items) {
            if (!productService.isExist(item.getProductId())
                    || item.getQuantity() < 0) {
                throw new ApiException(TransactionError.VALIDATION_ERROR);
            }
        }
    }

    @Override
    public TransactionType getType() {
        return type;
    }
}
