package com.careerbuilder.careerbuilder.domain.transaction.controller;

import com.careerbuilder.careerbuilder.domain.transaction.business.TransactionBusiness;
import com.careerbuilder.careerbuilder.domain.transaction.dto.TransactionDetailResponse;
import com.careerbuilder.careerbuilder.domain.transaction.dto.TransactionDetailWithProductListResponse;
import com.careerbuilder.careerbuilder.domain.transaction.dto.TransactionRegisterRequest;
import com.careerbuilder.careerbuilder.domain.transaction.dto.TransactionResponse;
import com.careerbuilder.careerbuilder.domain.transaction.entity.type.TransactionType;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private static final Logger log = LoggerFactory.getLogger(TransactionController.class);
    private final TransactionBusiness transactionBusiness;

    /**
     * 거래 리스트 조회 뷰
     *
     * @param model 거래 리스트를 담을 model 객체
     * @return view
     */
    @GetMapping
    public String transactions(
            Model model
    ) {
        List<TransactionDetailResponse> transactionList = transactionBusiness.getTransactionList();
        model.addAttribute("transactions", transactionList);
        return "transaction/list";
    }

    /**
     * 거래 상세 조회 뷰
     *
     * @param transactionId 거래식별자(ID)
     * @param model         거래 상세 정보를 담을 model 객체
     * @return view
     */
    @GetMapping("/{transactionId}")
    public String transactionDetail(
            @PathVariable Long transactionId,
            Model model
    ) {
        TransactionDetailWithProductListResponse transactionDetailWithProductList = transactionBusiness.getTransactionDetailWithProductListById(transactionId);
        model.addAttribute("transaction", transactionDetailWithProductList);
        return "transaction/detail";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("transaction", TransactionRegisterRequest.builder().build());
        return "transaction/addForm";
    }

    @PostMapping("/add")
    public String createTransaction(
            @ModelAttribute TransactionRegisterRequest request,
            RedirectAttributes redirectAttributes
    ) {
        log.info("transactionType = {}", request.getTransactionType());
        log.info("fromLocation = {}", request.getFromLocation());
        log.info("toLocation = {}", request.getToLocation());
        log.info("partner = {}", request.getPartner());
        log.info("memo = {}", request.getMemo());
        log.info("items = {}", request.getItems());

        TransactionResponse transaction = transactionBusiness.register(request);
        redirectAttributes.addAttribute("transactionId", transaction.getId());
        return "redirect:/transactions/{transactionId}";
    }

    @GetMapping("/{transactionId}/edit")
    public String editForm(
            @PathVariable Long transactionId,
            Model model
    ) {
        TransactionDetailWithProductListResponse transaction = transactionBusiness.getTransactionDetailWithProductListById(transactionId);
        model.addAttribute("transaction", transaction);
        return "transaction/editForm";
    }

    @PostMapping("/{transactionId}/edit")
    public String updateTransaction(
            @PathVariable Long transactionId,
            @ModelAttribute TransactionRegisterRequest transactionRegisterRequest
    ) {
        // TODO 거래 수정 추가, View 구현 한계로 임시적으로 구현 X
        return "redirect:/transactions/{transactionId}";
    }

    @GetMapping("/{transactionId}/delete")
    public String deleteTransaction(
            @PathVariable Long transactionId
    ) {
        transactionBusiness.deleteTransaction(transactionId);
        return "redirect:/transactions";
    }

    @ModelAttribute("transactionTypes")
    public TransactionType[] transactionTypes() {
        return TransactionType.values();
    }
}
