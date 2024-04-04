package com.careerbuilder.careerbuilder.domain.partner.dto;


import com.careerbuilder.careerbuilder.domain.partner.entity.type.PartnerType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PartnerRegisterRequest {

    @NotNull
    private PartnerType partnerType;

    @NotBlank
    private String name;

    private String phoneNumber;

    @Email
    private String email;

    private String address;
}
