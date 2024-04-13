package com.careerbuilder.careerbuilder.domain.transaction.dto;


import com.careerbuilder.careerbuilder.domain.transaction.entity.type.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionResponse {

    private Long id;
    private TransactionType transactionType;
}
