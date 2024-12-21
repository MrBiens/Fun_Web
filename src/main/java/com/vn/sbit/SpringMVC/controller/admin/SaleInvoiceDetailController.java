package com.vn.sbit.SpringMVC.controller.admin;

import com.vn.sbit.SpringMVC.service.SaleInvoiceDetailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/sale-detail")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class SaleInvoiceDetailController {
    SaleInvoiceDetailService saleInvoiceDetailService;


}