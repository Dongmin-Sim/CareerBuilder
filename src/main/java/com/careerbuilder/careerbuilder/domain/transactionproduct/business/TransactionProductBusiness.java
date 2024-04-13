package com.careerbuilder.careerbuilder.domain.transactionproduct.business;

import com.careerbuilder.careerbuilder.domain.transaction.converter.TransactionConverter;
import com.careerbuilder.careerbuilder.domain.transaction.dto.TransactionRegisterRequest;
import com.careerbuilder.careerbuilder.domain.transactionproduct.converter.TransactionProductConverter;
import com.careerbuilder.careerbuilder.domain.transactionproduct.dto.TransactionProductRequest;
import com.careerbuilder.careerbuilder.domain.transactionproduct.entity.TransactionProduct;
import com.careerbuilder.careerbuilder.domain.transactionproduct.service.TransactionProductService;
import com.careerbuilder.careerbuilder.global.common.annotation.Business;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.careerbuilder.careerbuilder.domain.transaction.dto.TransactionRegisterRequest.Item;

@Business
@RequiredArgsConstructor
public class TransactionProductBusiness {

    private final TransactionProductService transactionProductService;
    private final TransactionProductConverter transactionProductConverter;

    @Transactional
    public List<TransactionProduct> registerTransactionProductItemList(
            Long transactionId,
            List<Item> items
    )
    {
        List<TransactionProduct> transactionProducts =
                items.stream().map(
                item -> {
                    // item -> request dto
                    TransactionProductRequest request = TransactionProductRequest.builder()
                            .transactionId(transactionId)
                            .productId(item.getProductId())
                            .quantity(item.getQuantity())
                            .build();
                    // dto -> entity
                    return transactionProductConverter.toEntity(request);
                }
        ).collect(Collectors.toList());

        return transactionProductService.saveAll(transactionProducts);
    }
}
