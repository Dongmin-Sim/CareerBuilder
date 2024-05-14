package com.careerbuilder.careerbuilder.domain.product.controller;

import com.careerbuilder.careerbuilder.domain.product.business.ProductBusiness;
import com.careerbuilder.careerbuilder.domain.product.dto.ProductWithAttributionsResponse;
import com.careerbuilder.careerbuilder.domain.product.dto.ProductResponse;
import com.careerbuilder.careerbuilder.domain.product.dto.RegisterProductRequest;
import com.careerbuilder.careerbuilder.domain.product.dto.UpdateProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductBusiness productBusiness;

    @GetMapping
    public String products(Model model) {
        List<ProductResponse> products = productBusiness.getProductList();
        model.addAttribute("products", products);
        return "product/list";
    }

    @GetMapping("/{productId}")
    public String product(
            @PathVariable Long productId,
            Model model
    ) {
        ProductWithAttributionsResponse product = productBusiness.getProductWithAttributionsById(productId);
        model.addAttribute("product", product.getProductResponse());
        return "product/detail";
    }

    @GetMapping("/add")
    public String addForm(
            Model model
    ) {
        model.addAttribute("product", RegisterProductRequest.builder().build());
        return "product/addForm";
    }

    @PostMapping("/add")
    public String createProduct(
            @ModelAttribute RegisterProductRequest request,
            RedirectAttributes redirectAttributes
    ) {
        ProductResponse product = productBusiness.register(request);
        redirectAttributes.addAttribute("productId", product.getId());
        redirectAttributes.addAttribute("param", true);
        return "redirect:/products/{productId}";
    }

    @GetMapping("/{productId}/edit")
    public String editForm(
            @PathVariable Long productId,
            Model model
    ) {
        ProductWithAttributionsResponse product = productBusiness.getProductWithAttributionsById(productId);
        model.addAttribute("product", product.getProductResponse());
        return "product/editForm";
    }

    @PostMapping("/{productId}/edit")
    public String updateProduct(
            @PathVariable Long productId,
            @ModelAttribute UpdateProductRequest updateProductRequest
    ) {
        productBusiness.updateProductById(productId, updateProductRequest);
        return "redirect:/products/{productId}";
    }

    @GetMapping("/{productId}/delete")
    public String deleteProduct(
            @PathVariable Long productId
    ) {
        productBusiness.deleteProductById(productId);
        return "redirect:/products";
    }
}
