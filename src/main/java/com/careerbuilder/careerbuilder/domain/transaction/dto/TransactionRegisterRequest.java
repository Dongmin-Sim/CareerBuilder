package com.careerbuilder.careerbuilder.domain.transaction.dto;

import com.careerbuilder.careerbuilder.domain.transaction.entity.type.TransactionType;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionRegisterRequest {

    @NotBlank
    private TransactionType transactionType;

    private Long fromLocation;

    private Long toLocation;

    private Long partner;

    private String memo;

    private List<Item> items;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Item {
        Long productId;
        int quantity;
    }
}
