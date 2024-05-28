package com.careerbuilder.careerbuilder.domain.transaction.business;

import com.careerbuilder.careerbuilder.domain.location.business.LocationBusiness;
import com.careerbuilder.careerbuilder.domain.location.dto.LocationResponse;
import com.careerbuilder.careerbuilder.domain.location.service.LocationService;
import com.careerbuilder.careerbuilder.domain.partner.business.PartnerBusiness;
import com.careerbuilder.careerbuilder.domain.partner.dto.PartnerResponse;
import com.careerbuilder.careerbuilder.domain.product.business.ProductBusiness;
import com.careerbuilder.careerbuilder.domain.product.business.dto.ProductResponse;
import com.careerbuilder.careerbuilder.domain.product.service.ProductService;
import com.careerbuilder.careerbuilder.domain.transaction.business.transactionTypeAction.Action;
import com.careerbuilder.careerbuilder.domain.transaction.business.transactionTypeAction.ActionFactory;
import com.careerbuilder.careerbuilder.domain.transaction.converter.TransactionConverter;
import com.careerbuilder.careerbuilder.domain.transaction.dto.*;
import com.careerbuilder.careerbuilder.domain.transaction.entity.Transaction;
import com.careerbuilder.careerbuilder.domain.transaction.entity.type.TransactionStatus;
import com.careerbuilder.careerbuilder.domain.transaction.service.TransactionService;
import com.careerbuilder.careerbuilder.domain.transactionItem.business.TransactionItemBusiness;
import com.careerbuilder.careerbuilder.domain.transactionItem.dto.TransactionItemResponse;
import com.careerbuilder.careerbuilder.global.common.annotation.Business;
import com.careerbuilder.careerbuilder.global.common.error.ErrorCode;
import com.careerbuilder.careerbuilder.global.common.error.TransactionError;
import com.careerbuilder.careerbuilder.global.common.exception.ApiException;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Business
@RequiredArgsConstructor
public class TransactionBusiness {

    private final TransactionService transactionService;
    private final TransactionConverter transactionConverter;

    private final ActionFactory actionFactory;

    private final TransactionItemBusiness transactionItemBusiness;
    private final ProductBusiness productBusiness;
    private final PartnerBusiness partnerBusiness;
    private final LocationBusiness locationBusiness;

    private final LocationService locationService;
    private final ProductService productService;


    @Transactional
    public TransactionResponse register(TransactionRegisterRequest request) {
        validateRequest(request);
        Action action = actionFactory.getAction(request.getTransactionType());

        // new Transaction (register Item & update stock)
        Transaction txEntity = transactionConverter.toEntity(request);
        Transaction savedTx = transactionService.save(txEntity);

        request.getItems().forEach(item -> {
            action.registerTransactionItem(savedTx, item); // 거래 Item 등록
            action.updateStock(savedTx, item); // 재고 변경
        });

        return transactionConverter.toResponse(savedTx);
    }

    public List<TransactionDetailResponse> getTransactionList() {
        return transactionService.findAll().stream()
                .map(transactionConverter::toDetailResponse)
                .toList();
    }

    public TransactionDetailWithProductListResponse getTransactionDetailWithProductListById(@Positive Long transactionId) {
        // transaction 조회
        Transaction transaction = transactionService.findById(transactionId);

        // (fromloaction, tolocation)location 조회
        Long fromLocationId = transaction.getFromLocationId();
        Long toLocationId = transaction.getToLocationId();

        LocationResponse fromLocation = null;
        LocationResponse toLocation = null;
        if (fromLocationId != null) {
            fromLocation = locationBusiness.getLocationById(fromLocationId);
        }

        if (toLocationId != null) {
            toLocation = locationBusiness.getLocationById(toLocationId);
        }

        // (partner) 조회
        Long partnerId = transaction.getPartnerId();
        PartnerResponse partner = null;
        if (partnerId != null) {
            partner = partnerBusiness.getPartnerById(partnerId);
        }

        // (product) 리스트 조회 -> list<item> 생성
        List<TransactionItemResponse> transactionProductList = transactionItemBusiness.getTransactionItemList(transaction.getId());
        List<TransactionDetailWithProductListResponse.Item> items = new ArrayList<>();

        for (TransactionItemResponse transactionProduct : transactionProductList) {
            ProductResponse product = productBusiness.getProductById(transactionProduct.getProductId());

            items.add(new TransactionDetailWithProductListResponse.Item(product, transactionProduct.getQuantity()));
        }

        // TransactionDetailWithProductListResponse 생성 & 리턴
        return TransactionDetailWithProductListResponse.builder()
                .id(transaction.getId())
                .transactionType(transaction.getTransactionType())
                .fromLocation(fromLocation)
                .toLocation(toLocation)
                .partner(partner)
                .memo(transaction.getMemo())
                .status(transaction.getStatus())
                .items(items)
                .createdAt(transaction.getCreatedAt())
                .updatedAt(transaction.getUpdatedAt())
                .build();
    }

    @Transactional
    public TransactionResponse updateTransactionById(Long transactionId, TransactionUpdateRequest updateRequest) {
        // origin Transaction (rollback)
        Transaction originTx = transactionService.findById(transactionId);
        Action action = actionFactory.getAction(originTx.getTransactionType());

        if (originTx.getStatus().equals(TransactionStatus.ACTIVE)) {
            action.rollbackStock(originTx);
            originTx.updateStatus(TransactionStatus.MODIFIED);

            // new Transaction (register Item & update stock)
            Transaction updateEntity = transactionConverter.toUpdateEntity(
                    originTx.getId(),
                    originTx.getTransactionType(),
                    updateRequest
            );
            Transaction updatedTx = transactionService.save(updateEntity);

            updateRequest.getItems().forEach(item -> {
                action.registerTransactionItem(updatedTx, item);
                action.updateStock(updatedTx, item);
            });
            return transactionConverter.toResponse(updatedTx);
        }
        throw new ApiException(TransactionError.VALIDATION_ERROR, "Transaction is not active");
    }

    @Transactional
    public TransactionResponse deleteTransaction(Long transactionId) {
        Transaction originTx = transactionService.findById(transactionId);
        Action action = actionFactory.getAction(originTx.getTransactionType());

        // origin Transaction (rollback)
        if (originTx.getStatus().equals(TransactionStatus.ACTIVE)) {
            action.rollbackStock(originTx);
            originTx.updateStatus(TransactionStatus.DELETED);
            return transactionConverter.toResponse(originTx);
        }
        throw new ApiException(TransactionError.VALIDATION_ERROR, "Transaction is not active");
    }

    public void validateRequest(TransactionRegisterRequest request) {
        if (request.getTransactionType() == null) {
            throw new ApiException(ErrorCode.NULL_POINT_ERROR);
        }

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

        request.getItems().forEach(item -> {
            if (!productService.isExist(item.getProductId())
                    || item.getQuantity() < 0) {
                throw new ApiException(TransactionError.VALIDATION_ERROR);
            }
        });
    }
}
