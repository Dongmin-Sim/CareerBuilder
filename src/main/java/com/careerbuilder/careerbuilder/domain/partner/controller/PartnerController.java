package com.careerbuilder.careerbuilder.domain.partner.controller;

import com.careerbuilder.careerbuilder.domain.partner.business.PartnerBusiness;
import com.careerbuilder.careerbuilder.domain.partner.dto.PartnerRegisterRequest;
import com.careerbuilder.careerbuilder.domain.partner.dto.PartnerResponse;
import com.careerbuilder.careerbuilder.domain.partner.dto.UpdatePartnerRequest;
import com.careerbuilder.careerbuilder.domain.partner.entity.type.PartnerType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/partners")
@RequiredArgsConstructor
public class PartnerController {

    private final PartnerBusiness partnerBusiness;

    @GetMapping
    public String partners(
            Model model
    ) {
        List<PartnerResponse> partnerList = partnerBusiness.getPartnerList();
        model.addAttribute("partners", partnerList);
        return "partner/list";
    }

    @GetMapping("/{partnerId}")
    public String partner(
            @PathVariable Long partnerId,
            Model model
    ) {
        PartnerResponse partner = partnerBusiness.getPartnerById(partnerId);
        model.addAttribute("partner", partner);
        return "partner/detail";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("partner", PartnerRegisterRequest.builder().build());
        return "partner/addForm";
    }

    @PostMapping("/add")
    public String createPartner(
            @ModelAttribute PartnerRegisterRequest partnerRegisterRequest,
            RedirectAttributes redirectAttributes
    ) {
        PartnerResponse partner = partnerBusiness.register(partnerRegisterRequest);

        redirectAttributes.addAttribute("partnerId", partner.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/partners/{partnerId}";
    }

    @GetMapping("/{partnerId}/edit")
    public String editForm(
            @PathVariable Long partnerId,
            Model model
    ) {
        PartnerResponse partner = partnerBusiness.getPartnerById(partnerId);
        model.addAttribute("partner", partner);
        return "partner/editForm";
    }

    @PostMapping("/{partnerId}/edit")
    public String updatePartner(
            @PathVariable Long partnerId,
            @ModelAttribute UpdatePartnerRequest updatePartnerRequest
    ) {
        partnerBusiness.updatePartnerById(partnerId, updatePartnerRequest);
        return "redirect:/partners/{partnerId}";
    }

    @GetMapping("/{partnerId}/delete")
    public String deletePartner(
            @PathVariable Long partnerId
    ) {
        partnerBusiness.deletePartnerById(partnerId);
        return "redirect:/partners";
    }

    @ModelAttribute("partnerTypes")
    public PartnerType[] types() {
        return PartnerType.values();
    }
}
