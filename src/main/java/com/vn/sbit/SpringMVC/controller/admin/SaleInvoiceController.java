package com.vn.sbit.SpringMVC.controller.admin;

import com.vn.sbit.SpringMVC.dto.request.SaleInvoiceRequest;
import com.vn.sbit.SpringMVC.dto.response.CustomerResponse;
import com.vn.sbit.SpringMVC.dto.response.EmployeeResponse;
import com.vn.sbit.SpringMVC.dto.response.SaleInvoiceResponse;
import com.vn.sbit.SpringMVC.service.CustomerService;
import com.vn.sbit.SpringMVC.service.EmployeeService;
import com.vn.sbit.SpringMVC.service.SaleInvoiceService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequestMapping("/admin/sale")
public class SaleInvoiceController {
    SaleInvoiceService saleInvoiceService;
    EmployeeService employeeService;
    CustomerService customerService;

    @GetMapping("/home")
    public String home(Model model){
        List<SaleInvoiceResponse> list_sale = saleInvoiceService.getAll();
        model.addAttribute("list_sale",list_sale);
        return "admin/sale/index";
    }

    @GetMapping("/add")
    public String add(Model model){
        SaleInvoiceRequest request = new SaleInvoiceRequest();
        model.addAttribute("request",request);

        List<EmployeeResponse> list_employee = employeeService.getAll();
        model.addAttribute("list_employee",list_employee);

        List<CustomerResponse> list_customer= customerService.getAll();
        model.addAttribute("list_customer",list_customer);
        return "admin/sale/add";
    }
    @PostMapping("/add")
    public String add(@ModelAttribute("request")SaleInvoiceRequest request){
        saleInvoiceService.create(request);
        return "redirect:/admin/sale/home";
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id,Model model){
        model.addAttribute("id",id);
        SaleInvoiceResponse findResponse = saleInvoiceService.findById(id);

        SaleInvoiceRequest request =SaleInvoiceRequest.builder()
                .invoiceName(findResponse.getInvoiceName())
                .customerId(findResponse.getCustomer().getId())
                .employeeId(findResponse.getEmployee().getId())
                .build();
        model.addAttribute("request",request);
        return "admin/sale/edit";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute("request") SaleInvoiceRequest request,@RequestParam Long id){
        saleInvoiceService.updateSaleInvoice(id,request);
        return "redirect:/admin/sale/home";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        saleInvoiceService.deleteById(id);
        return "redirect:/admin/sale/home";
    }



}
