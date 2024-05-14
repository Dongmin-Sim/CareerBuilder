package com.careerbuilder.careerbuilder.domain.stock.controller;

import com.careerbuilder.careerbuilder.domain.stock.business.StockBusiness;
import com.careerbuilder.careerbuilder.domain.stock.dto.StockDetailResponse;
import com.careerbuilder.careerbuilder.domain.stock.dto.StockQuantity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/stocks")
@RequiredArgsConstructor
public class StockController {

    private final StockBusiness stockBusiness;

    @GetMapping
    public String stocks(
            Model model
    ) {
        List<StockQuantity> stockQuantityList = stockBusiness.getStockTotalQuantityListByLocation();
        model.addAttribute("stocks", stockQuantityList);

        return "stock/list";
    }

    @GetMapping("/{locationId}")
    public String stock(
            @PathVariable Long locationId,
            Model model
    ) {
        StockDetailResponse stock = stockBusiness.getStockListByLocationId(locationId);
        model.addAttribute("stock", stock);

        return "stock/detail";
    }
}
