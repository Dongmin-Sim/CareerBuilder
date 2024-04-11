package com.careerbuilder.careerbuilder.domain.attribution.entity;

import com.careerbuilder.careerbuilder.global.common.baseentity.BaseEntity;
import com.careerbuilder.careerbuilder.domain.attribution.entity.type.AttributionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

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
    private String attributionName;

    private Integer rankNum;

    public void updateName(String attributionName) {
        this.attributionName = attributionName;
    }

    public void updateRank(Integer rankNum) {
        this.rankNum = rankNum;
    }
}
