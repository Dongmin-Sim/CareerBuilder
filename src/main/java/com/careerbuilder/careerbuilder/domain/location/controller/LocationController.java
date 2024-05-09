package com.careerbuilder.careerbuilder.domain.location.controller;

import com.careerbuilder.careerbuilder.domain.location.business.LocationBusiness;
import com.careerbuilder.careerbuilder.domain.location.dto.LocationRequest;
import com.careerbuilder.careerbuilder.domain.location.dto.LocationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/locations")
@RequiredArgsConstructor
public class LocationController {

    private final LocationBusiness locationBusiness;

    @GetMapping
    public String locations(Model model) {
        List<LocationResponse> locationList = locationBusiness.getLocationList();
        model.addAttribute("locations", locationList);
        return "location/list";
    }

    @GetMapping("/{locationId}")
    public String locationDetail(
            @PathVariable Long locationId,
            Model model
    ) {
        LocationResponse location = locationBusiness.getLocationById(locationId);
        model.addAttribute("location", location);
        return "location/detail";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("location", LocationRequest.builder().build());
        return "location/addForm";
    }

    @PostMapping("/add")
    public String createLocation(
            @ModelAttribute LocationRequest locationRequest,
            RedirectAttributes redirectAttributes
    ) {
        LocationResponse location = locationBusiness.register(locationRequest);

        redirectAttributes.addAttribute("locationId", location.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/locations/{locationId}";
    }

    @GetMapping("/{locationId}/edit")
    public String editForm(
            @PathVariable Long locationId,
            Model model
    ) {
        LocationResponse location = locationBusiness.getLocationById(locationId);
        model.addAttribute("location", location);
        return "location/editForm";
    }

    @PostMapping("/{locationId}/edit")
    public String updateLocation(
            @PathVariable Long locationId,
            @ModelAttribute LocationRequest locationRequest
    ) {
        locationBusiness.updateLocationById(locationId, locationRequest);
        return "redirect:/locations/{locationId}";
    }

    @GetMapping("/{locationId}/delete")
    public String deleteLocation(
            @PathVariable Long locationId
    ) {
        locationBusiness.deleteLocationById(locationId);
        return "redirect:/locations";
    }
}
