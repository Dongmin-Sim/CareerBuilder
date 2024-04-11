package com.careerbuilder.careerbuilder.domain.partner.controller;

import com.careerbuilder.careerbuilder.domain.partner.business.PartnerBusiness;
import com.careerbuilder.careerbuilder.domain.partner.dto.PartnerRegisterRequest;
import com.careerbuilder.careerbuilder.domain.partner.dto.PartnerResponse;
import com.careerbuilder.careerbuilder.domain.partner.dto.UpdatePartnerRequest;
import com.careerbuilder.careerbuilder.global.common.api.Api;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/partners")
@RequiredArgsConstructor
public class PartnerApiController {

    private final PartnerBusiness partnerBusiness;

    /**
     * 거래처 생성
     *
     * @param request 거래처 생성 요청 객체
     * @return 생성된 거래처 단건 정보를 담은 표준 API 응답
     */
    @PostMapping
    public Api<PartnerResponse> register(
            @RequestBody @Valid
            PartnerRegisterRequest request
    ) {
        var response = partnerBusiness.register(request);
        return Api.OK(response);
    }

    /**
     * 거래처 리스트 조회
     *
     * @return 거래처 리스트 정보를 담은 표준 API 응답
     */
    @GetMapping
    public Api<List<PartnerResponse>> getPartnerList() {
        var response = partnerBusiness.getPartnerList();
        return Api.OK(response);
    }

    /**
     * 거래처 단건 조회
     *
     * @param partnerId 거래처 ID
     * @return Id로 조회된 거래처 단건 정보를 담은 표준 API 응답
     */
    @GetMapping("/{partnerId}")
    public Api<PartnerResponse> getPartnerById(
            @PathVariable @Positive Long partnerId
    ) {
        var response = partnerBusiness.getPartnerById(partnerId);
        return Api.OK(response);
    }

    /**
     * 거래처 변경
     *
     * @param partnerId 거래처 ID
     * @param request   거래처 변경 데이터를 담은 요청 정보
     * @return 변경된 거래처 단건 정보를 담은 표준 API 응답
     */
    @PutMapping("/{partnerId}")
    public Api<PartnerResponse> updatePartner(
            @PathVariable @Positive Long partnerId,
            @RequestBody @Valid UpdatePartnerRequest request
    ) {
        var response = partnerBusiness.updatePartnerById(partnerId, request);
        return Api.OK(response);
    }

    /**
     * 거래처 부분 변경
     *
     * @param partnerId 거래처 ID
     * @param request   거래처 변경 데이터를 담은 요청 정보
     * @return 부분 변경된 거래처 단건 정보를 담은 표준 API 응답
     */
    @PatchMapping("/{partnerId}")
    public Api<PartnerResponse> partialUpdatePartner(
            @PathVariable @Positive Long partnerId,
            @RequestBody @Valid UpdatePartnerRequest request
    ) {
        var response = partnerBusiness.partialUpdatePartnerById(partnerId, request);
        return Api.OK(response);
    }

    /**
     * 거래처 삭제
     * @param partnerId 거래처 ID
     * @return 성공 여부를 알리는 표준 API 응답
     */
    @DeleteMapping("/{partnerId}")
    public Api<PartnerResponse> deletePartner(
            @PathVariable @Positive Long partnerId
    ) {
        partnerBusiness.deletePartnerById(partnerId);
        return Api.OK(null);
    }
}
