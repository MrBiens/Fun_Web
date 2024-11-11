package com.vn.sbit.SpringMVC.controller.admin;

import com.vn.sbit.SpringMVC.dto.response.PurchaseDetailResponse;
import com.vn.sbit.SpringMVC.service.PurchaseDetailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/purchase-detail")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class PurchaseDetailController {
    PurchaseDetailService purchaseDetailService;

    //get All PurchaseInvoiceDetail
    @GetMapping("/home")
    public String home(Model model){
        List<PurchaseDetailResponse> list_purchasedetail=purchaseDetailService.getAll();
        model.addAttribute("list_purchasedetail",list_purchasedetail);
        return "admin/purchasedetail/index";
    }

    @GetMapping("/index/{id}")
    public String getPurchase(){

        return "";
    }
}