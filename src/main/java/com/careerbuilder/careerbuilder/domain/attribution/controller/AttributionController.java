package com.careerbuilder.careerbuilder.domain.attribution.controller;

import com.careerbuilder.careerbuilder.domain.attribution.business.AttributionBusiness;
import com.careerbuilder.careerbuilder.domain.attribution.dto.AttributionResponse;
import com.careerbuilder.careerbuilder.domain.attribution.dto.RegisterAttributionRequest;
import com.careerbuilder.careerbuilder.domain.attribution.dto.UpdateAttributionNameRequest;
import com.careerbuilder.careerbuilder.domain.attribution.entity.type.AttributionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/attributions")
@RequiredArgsConstructor
public class AttributionController {

    private final AttributionBusiness attributionBusiness;

    @GetMapping
    public String attributions(Model model) {
        List<AttributionResponse> attributionList = attributionBusiness.getAttributionList();
        model.addAttribute("attributions", attributionList);
        return "attribution/list";
    }

    @GetMapping("/{attributionId}")
    public String attributionDetail(
            @PathVariable Long attributionId,
            Model model
    ) {
        AttributionResponse attribution = attributionBusiness.getAttributionById(attributionId);
        model.addAttribute("attribution", attribution);
        return "attribution/detail";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("attribution", RegisterAttributionRequest.builder().build());
        return "attribution/addForm";
    }

    @PostMapping("/add")
    public String createAttribution(
            @ModelAttribute RegisterAttributionRequest attributionRequest,
            RedirectAttributes redirectAttributes
    ) {
        AttributionResponse attribution = attributionBusiness.register(attributionRequest);

        redirectAttributes.addAttribute("attributionId", attribution.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/attributions/{attributionId}";
    }

    @GetMapping("/{attributionId}/edit")
    public String editForm(
            @PathVariable Long attributionId,
            Model model
    ) {
        AttributionResponse attribution = attributionBusiness.getAttributionById(attributionId);
        model.addAttribute("attribution", attribution);
        return "attribution/editForm";
    }

    @PostMapping("/{attributionId}/edit")
    public String updateAttributionName(
            @PathVariable Long attributionId,
            @ModelAttribute UpdateAttributionNameRequest updateAttributionNameRequest
    ) {
        AttributionResponse response = attributionBusiness.updateAttributionNameById(attributionId, updateAttributionNameRequest);
        return "redirect:/attributions/{attributionId}";
    }

    @GetMapping("/{attributionId}/delete")
    public String deleteAttribution(
            @PathVariable Long attributionId
    ) {
        attributionBusiness.deleteAttribution(attributionId);
        return "redirect:/attributions";
    }

    @ModelAttribute("attributionTypes")
    public AttributionType[] attributionTypes() {
        return AttributionType.values();
    }
}
