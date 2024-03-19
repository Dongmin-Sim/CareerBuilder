package com.careerbuilder.careerbuilder.domain.attribution.entity;

import com.careerbuilder.careerbuilder.global.common.baseentity.BaseEntity;
import com.careerbuilder.careerbuilder.domain.attribution.type.AttributionType;
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
@Table(name = "attribution")
public class Attribution extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AttributionType attributionType;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 100)
    private String value;

    private int order;
}
