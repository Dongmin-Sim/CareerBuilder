package com.careerbuilder.careerbuilder.domain.partner.dto;


import com.careerbuilder.careerbuilder.domain.partner.entity.type.PartnerType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PartnerResponse {

    private Long id;

    private PartnerType partnerType;

    private String name;

    private String phoneNumber;

    private String email;

    private String address;
}
