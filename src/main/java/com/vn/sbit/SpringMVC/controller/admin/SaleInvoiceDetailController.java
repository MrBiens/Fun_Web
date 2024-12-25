package com.vn.sbit.SpringMVC.controller.admin;

import com.vn.sbit.SpringMVC.dto.request.SaleInvoiceDetailRequest;
import com.vn.sbit.SpringMVC.dto.response.PurchaseDetailResponse;
import com.vn.sbit.SpringMVC.dto.response.SaleInvoiceDetailResponse;
import com.vn.sbit.SpringMVC.entity.SaleInvoiceDetail;
import com.vn.sbit.SpringMVC.service.SaleInvoiceDetailService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/sale-detail")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class SaleInvoiceDetailController {
    SaleInvoiceDetailService saleInvoiceDetailService;

    @GetMapping("/index/{saleId}")
    public String getPurchase(@PathVariable("saleId")Long saleInvoiceId, Model model){
        List<SaleInvoiceDetailResponse> list=saleInvoiceDetailService.findBySaleInvoiceId(saleInvoiceId);
        model.addAttribute("list",list);

        model.addAttribute("saleInvoiceId",saleInvoiceId);

        int totalAmount = saleInvoiceDetailService.calculateTotalAmountByInvoiceId(saleInvoiceId);
        model.addAttribute("totalAmount",totalAmount);

        int totalQuantity = saleInvoiceDetailService.calculateTotalQuantityByInvoiceId(saleInvoiceId);
        model.addAttribute("totalQuantity",totalQuantity);

        return "admin/saleDetail/detail";
    }

    @GetMapping("/add/{saleId}")
    public String add(@PathVariable("saleId")Long saleInvoiceId,Model model){
        SaleInvoiceDetailRequest request= new SaleInvoiceDetailRequest();
        request.setSaleInvoiceId(saleInvoiceId);

        log.info("Request Sale Invoice Detail Khi gui sang view :{}",request);

        model.addAttribute("request",request);

        return "admin/saleDetail/add";
    }
    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("request")SaleInvoiceDetailRequest request, RedirectAttributes redirectAttributes){
        log.info("Request Sale Invoice Detail khi gui ve controller :{}",request);
        saleInvoiceDetailService.create(request);

        Long saleId= request.getSaleInvoiceId();
        redirectAttributes.addAttribute("saleId",saleId);
        return "redirect:/admin/sale-detail/index/{saleId}";
    }

    @GetMapping("/edit/{id}")
    public String update(@PathVariable("id")Long id,Model model){
        SaleInvoiceDetailResponse saleInvoiceDetail = saleInvoiceDetailService.findSaleInvoiceDetailById(id);

        SaleInvoiceDetailRequest request = SaleInvoiceDetailRequest.builder()
                .quantity(saleInvoiceDetail.getQuantity())
                .price(saleInvoiceDetail.getPrice())
                .build();
        model.addAttribute("request",request);
        return "admin/saleDetail/edit";
    }
    @PostMapping("/edit")
    public String edit(){


        return "redirect:/admin/sale-detail/index/{saleId}";
    }


}