package com.careerbuilder.careerbuilder.domain.transaction.dto;

import com.careerbuilder.careerbuilder.domain.location.entity.Location;
import com.careerbuilder.careerbuilder.domain.partner.entity.Partner;
import com.careerbuilder.careerbuilder.domain.product.entity.Product;
import com.careerbuilder.careerbuilder.domain.transaction.entity.type.TransactionStatusType;
import com.careerbuilder.careerbuilder.domain.transaction.entity.type.TransactionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionRegisterRequest {

    @NotBlank
    private String transactionType;

    private BigDecimal fromLocation;

    private BigDecimal toLocation;

    private BigDecimal partner;

    private String memo;

    private List<Map<String, Integer>> products;
}
