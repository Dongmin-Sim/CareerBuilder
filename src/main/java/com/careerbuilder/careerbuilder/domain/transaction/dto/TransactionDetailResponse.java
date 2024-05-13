package com.careerbuilder.careerbuilder.domain.transaction.dto;

import com.careerbuilder.careerbuilder.domain.transaction.entity.type.TransactionStatus;
import com.careerbuilder.careerbuilder.domain.transaction.entity.type.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionDetailResponse {

    private Long id;

    private TransactionType transactionType;

    private Long fromLocation;

    private Long toLocation;

    private Long partner;

    private String memo;

    private TransactionStatus status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
