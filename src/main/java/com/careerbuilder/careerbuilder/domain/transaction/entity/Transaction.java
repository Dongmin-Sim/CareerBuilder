package com.careerbuilder.careerbuilder.domain.transaction.entity;

import com.careerbuilder.careerbuilder.domain.location.entity.Location;
import com.careerbuilder.careerbuilder.domain.partner.entity.Partner;
import com.careerbuilder.careerbuilder.domain.transaction.type.TransactionStatusType;
import com.careerbuilder.careerbuilder.domain.transaction.type.TransactionType;
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

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @ManyToOne
    @JoinColumn(name = "partner_id")
    private Partner partner;

    private int quantity;

    @Column(length = 200)
    private String memo;

    @Enumerated(EnumType.STRING)
    private TransactionStatusType status;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
}
