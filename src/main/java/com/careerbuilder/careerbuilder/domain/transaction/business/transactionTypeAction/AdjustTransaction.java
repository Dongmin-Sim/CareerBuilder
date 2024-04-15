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

import java.util.List;

@Component
@RequiredArgsConstructor
public class AdjustTransaction implements TypeTransaction {
    private final TransactionType type = TransactionType.ADJUST;

    private final TransactionProductBusiness transactionProductBusiness;
    private final StockBusiness stockBusiness;

    private final TransactionService transactionService;
    private final LocationService locationService;
    private final ProductService productService;

    private final TransactionConverter transactionConverter;

    @Override
    public TransactionResponse action(TransactionRegisterRequest request) {
        validateRequest(request);

        Transaction txEntity = transactionConverter.toEntity(request);
        Transaction registerTx = transactionService.save(txEntity);

        transactionProductBusiness.registerTransactionProductItemList(
                registerTx.getId(),
                request.getItems()
        );

        stockBusiness.adjustStockWithItemList(
                request.getToLocation(),
                request.getItems()
        );

        return transactionConverter.toResponse(registerTx);
    }

    @Override
    public void validateRequest(TransactionRegisterRequest request) {
        if (request.getToLocation() == null) {
            throw new ApiException(TransactionError.VALIDATION_ERROR, "입고지가 필요합니다.");
        }
        if (request.getItems().isEmpty()) {
            throw new ApiException(TransactionError.VALIDATION_ERROR, "최소 하나 이상의 제품이 필요합니다.");
        }
        Long toLocation = request.getToLocation();
        if (!locationService.isExist(toLocation)) {
            throw new ApiException(TransactionError.VALIDATION_ERROR, "장소가 등록되지 않았습니다.");
        }
        List<TransactionRegisterRequest.Item> items = request.getItems();
        for (TransactionRegisterRequest.Item item : items) {
            if (!productService.isExist(item.getProductId())) {
                throw new ApiException(TransactionError.VALIDATION_ERROR, "상품이 등록되지 않았습니다.");
            }
        }
    }

    @Override
    public TransactionType getType() {
        return type;
    }
}
