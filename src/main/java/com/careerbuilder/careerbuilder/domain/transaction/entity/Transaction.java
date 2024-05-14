package com.careerbuilder.careerbuilder.domain.transaction.entity;

import com.careerbuilder.careerbuilder.domain.transaction.dto.TransactionUpdateRequest;
import com.careerbuilder.careerbuilder.domain.transaction.entity.type.TransactionStatus;
import com.careerbuilder.careerbuilder.domain.transaction.entity.type.TransactionType;
import com.careerbuilder.careerbuilder.global.common.baseentity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "transaction")
public class Transaction extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long originTransactionId;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    private Long fromLocationId;

    private Long toLocationId;

    private Long partnerId;

    @Column(length = 200)
    private String memo;

    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    public void updateStatus(TransactionStatus status) {
        this.status = status;
    }

    public void update(Transaction entity) {
        this.transactionType = entity.transactionType;

    }
    public void update(TransactionUpdateRequest updateRequest, TransactionStatus status) {
        this.fromLocationId = updateRequest.getFromLocation();
        this.toLocationId = updateRequest.getToLocation();
        this.partnerId = updateRequest.getPartner();
        this.memo = updateRequest.getMemo();
        this.status = status;
    }
}
