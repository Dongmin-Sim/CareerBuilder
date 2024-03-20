package com.careerbuilder.careerbuilder.domain.partner.entity;

import com.careerbuilder.careerbuilder.domain.partner.type.PartnerType;
import com.careerbuilder.careerbuilder.global.common.baseentity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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
@Table(name = "partner")
public class Partner extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PartnerType partnerType;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 100)
    private String phoneNumber;

    @Column(length = 100)
    @Email
    private String email;

    @Column(length = 50)
    private String address;
}
