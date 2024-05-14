package com.careerbuilder.careerbuilder.domain.partner.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdatePartnerRequest {

    @NotBlank
    private String name;

    private String phoneNumber;

    @Email
    private String email;

    private String address;
}
