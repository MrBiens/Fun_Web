package com.vn.sbit.SpringMVC.controller.admin;

import com.vn.sbit.SpringMVC.dto.request.ProductRequest;
import com.vn.sbit.SpringMVC.dto.request.purchase.PurchaseCreateRequest;
import com.vn.sbit.SpringMVC.dto.request.purchase.PurchaseUpdateRequest;
import com.vn.sbit.SpringMVC.dto.request.supp.SupplierCreateRequest;
import com.vn.sbit.SpringMVC.dto.request.supp.SupplierUpdateRequest;
import com.vn.sbit.SpringMVC.dto.response.ProductResponse;
import com.vn.sbit.SpringMVC.dto.response.PurchaseResponse;
import com.vn.sbit.SpringMVC.dto.response.SupplierResponse;
import com.vn.sbit.SpringMVC.entity.PurchaseInvoice;
import com.vn.sbit.SpringMVC.entity.Supplier;
import com.vn.sbit.SpringMVC.service.PurchaseDetailService;
import com.vn.sbit.SpringMVC.service.PurchaseService;
import com.vn.sbit.SpringMVC.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/admin/purchase")
public class PurchaseInvoiceController {
    private final PurchaseService purchaseService;
    private final SupplierService supplierService;


    @Autowired
    public PurchaseInvoiceController(PurchaseService purchaseService,SupplierService supplierService) {
        this.purchaseService = purchaseService;
        this.supplierService=supplierService;
    }

    @GetMapping("/home")
    public String index(Model model){
        List<PurchaseResponse> purchaseInvoices = purchaseService.getAll();

        model.addAttribute("list_purchase",purchaseInvoices);
//        Double totalAmount=purchaseDetailService.calculateTotalQuantityByInvoiceId()
        return "admin/purchase/index";
    }
    @GetMapping("/add")
    public String add(Model model){
        PurchaseCreateRequest request = new PurchaseCreateRequest();
        model.addAttribute("request",request);

        List<SupplierResponse> supplierList=supplierService.getAll();
        model.addAttribute("list_supplier",supplierList);

        return "admin/purchase/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("request") PurchaseCreateRequest request, BindingResult result, Model model){

        if (result.hasErrors()) {
            model.addAttribute("message_error", "PurchaseInvoiceName not success");
            return "redirect:/admin/purchase/add"; // Trả về trang thêm sản phẩm
        }
        PurchaseResponse response = purchaseService.create(request);
        if(response != null){
            return "redirect:/admin/purchase/home";
        }else{
            return "admin/purchase/add";
        }
    }

    @GetMapping("/edit/{id}")
    public String update(@PathVariable("id") Long id, Model model){
        PurchaseInvoice purchaseInvoice=purchaseService.findById(id);

        model.addAttribute("id",id); //id purchase


        List<SupplierResponse> supplierList=supplierService.getAll();
        model.addAttribute("list_supplier",supplierList);

        PurchaseUpdateRequest request = new PurchaseUpdateRequest();
        request.setPurchaseInvoiceName(purchaseInvoice.getPurchaseInvoiceName());
        request.setSupplierId(purchaseInvoice.getSupplier().getId());

        model.addAttribute("purchaseUpdate",request);
        return "admin/purchase/edit";
    }


    @PostMapping("/edit/{id}")
    public String update(@PathVariable("id")Long id,@ModelAttribute("purchaseUpdate")PurchaseUpdateRequest request){
        if(request != null) {
            purchaseService.updateById(id, request);
            return "redirect:/admin/purchase/home";
        }else {
            return "admin/purchase/edit";
        }
    }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        purchaseService.deleteById(id);
        return "redirect:/admin/purchase/home"; //load controller url
    }



}
