package com.careerbuilder.careerbuilder.domain.transactionItem.business;

import com.careerbuilder.careerbuilder.domain.transactionItem.converter.TransactionItemConverter;
import com.careerbuilder.careerbuilder.domain.transactionItem.dto.TransactionItemRequest;
import com.careerbuilder.careerbuilder.domain.transactionItem.dto.TransactionItemResponse;
import com.careerbuilder.careerbuilder.domain.transactionItem.entity.TransactionItem;
import com.careerbuilder.careerbuilder.domain.transactionItem.service.TransactionItemService;
import com.careerbuilder.careerbuilder.global.common.annotation.Business;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Business
@RequiredArgsConstructor
public class TransactionItemBusiness {

    private final TransactionItemService transactionItemService;
    private final TransactionItemConverter transactionItemConverter;

    @Transactional
    public TransactionItemResponse register(
            TransactionItemRequest request
    ) {
        TransactionItem transactionItem = transactionItemConverter.toEntity(request);
        TransactionItem save = transactionItemService.save(transactionItem);
        return transactionItemConverter.toResponse(save);
    }

    public List<TransactionItemResponse> getTransactionItemList(Long transactionId) {
        List<TransactionItem> transactionItemList = transactionItemService.findTransactionItemListById(transactionId);
        return transactionItemList.stream()
                .map(transactionItem -> {
                    return transactionItemConverter.toResponse(transactionItem);
                }).toList();
    }
}
