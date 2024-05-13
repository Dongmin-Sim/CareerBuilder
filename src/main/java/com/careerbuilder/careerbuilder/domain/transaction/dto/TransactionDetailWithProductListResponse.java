package com.careerbuilder.careerbuilder.domain.transaction.dto;

import com.careerbuilder.careerbuilder.domain.location.dto.LocationResponse;
import com.careerbuilder.careerbuilder.domain.partner.dto.PartnerResponse;
import com.careerbuilder.careerbuilder.domain.product.dto.ProductResponse;
import com.careerbuilder.careerbuilder.domain.transaction.entity.type.TransactionStatus;
import com.careerbuilder.careerbuilder.domain.transaction.entity.type.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionDetailWithProductListResponse {

    private Long id;

    private TransactionType transactionType;

    private LocationResponse fromLocation;

    private LocationResponse toLocation;

    private PartnerResponse partner;

    private String memo;

    private TransactionStatus status;

    private List<Item> items;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Item {
        ProductResponse product;
        int quantity;
    }
}
