package com.vn.sbit.SpringMVC.controller.admin;

import com.vn.sbit.SpringMVC.dto.request.PurchaseDetailRequest;
import com.vn.sbit.SpringMVC.dto.request.purchase.PurchaseCreateRequest;
import com.vn.sbit.SpringMVC.dto.request.purchase.PurchaseUpdateRequest;
import com.vn.sbit.SpringMVC.dto.response.PurchaseDetailResponse;
import com.vn.sbit.SpringMVC.dto.response.PurchaseResponse;
import com.vn.sbit.SpringMVC.dto.response.SupplierResponse;
import com.vn.sbit.SpringMVC.entity.ProductSupplier;
import com.vn.sbit.SpringMVC.entity.PurchaseInvoice;
import com.vn.sbit.SpringMVC.repository.ProductSupplierRepository;
import com.vn.sbit.SpringMVC.service.PurchaseDetailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/purchase-detail")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class PurchaseDetailController {
    private static final Logger log = LoggerFactory.getLogger(PurchaseDetailController.class);
    PurchaseDetailService purchaseDetailService;
    ProductSupplierRepository productSupplierRepository;



    //get All PurchaseInvoiceDetail
    @GetMapping("/home")
    public String home(Model model){
        List<PurchaseDetailResponse> list_purchasedetail=purchaseDetailService.getAll();
        model.addAttribute("list_purchasedetail",list_purchasedetail);
        return "admin/purchasedetail/index";
    }

    @GetMapping("/index/{purchaseId}")
    public String getPurchase(@PathVariable("purchaseId")Long purchaseInvoiceId,Model model){
        List<PurchaseDetailResponse> list=purchaseDetailService.getPurchaseInvoiceDetailsByPurchaseInvoiceId(purchaseInvoiceId);
        model.addAttribute("list",list);

        model.addAttribute("purchaseInvoiceId",purchaseInvoiceId);
        return "admin/purchasedetail/detail";
    }

    @GetMapping("/add/{purchaseId}")
    public String add(@PathVariable("purchaseId")Long purchaseId, Model model){
        PurchaseDetailRequest request = new PurchaseDetailRequest();
        request.setPurchaseInvoiceId(purchaseId);

        model.addAttribute("request",request);

        model.addAttribute("purchaseId",purchaseId); // xem đó là hóa đơn nào - who supplier

        List<ProductSupplier> productSupllier = productSupplierRepository.findAll();
        model.addAttribute("list_productSupplier",productSupllier); // lấy productsupplier xem user nhập product của supplier nào -> trả về productSupplierId

        return "admin/purchasedetail/add";
    }
    @PostMapping("/add")
    public String add(@ModelAttribute("request") PurchaseDetailRequest request, Model model, RedirectAttributes redirectAttributes){
        PurchaseDetailResponse response = purchaseDetailService.create(request);

        Long purchaseId = request.getPurchaseInvoiceId();
        redirectAttributes.addAttribute("purchaseId", purchaseId); // thêm purchaseId vào đường dẫn
        return "redirect:/admin/purchase-detail/index/{purchaseId}";
    }

//    @GetMapping("/edit/{id}")
//    public String update(@PathVariable("id") Long id, Model model){
//        PurchaseInvoice purchaseInvoice=purchaseService.findById(id);
//        model.addAttribute("id",id);
//
//
//        List<SupplierResponse> supplierList=supplierService.getAll();
//        model.addAttribute("list_supplier",supplierList);
//
//        PurchaseUpdateRequest request = new PurchaseUpdateRequest();
//        request.setPurchaseInvoiceName(purchaseInvoice.getPurchaseInvoiceName());
//        request.setSupplierId(purchaseInvoice.getSupplier().getId());
//
//        model.addAttribute("purchaseUpdate",request);
//        return "admin/purchase/edit";
//    }
//


//
//    @PostMapping("/edit")
//    public String update(@RequestParam("id")Long id,@ModelAttribute("purchaseUpdate")PurchaseUpdateRequest request){
//        if(request != null) {
//            purchaseService.updateById(id, request);
//            return "redirect:/admin/purchase/home";
//        }else {
//            return "admin/purchase/edit";
//        }
//    }
//
//
//    @GetMapping("/delete/{id}")
//    public String delete(@PathVariable("id") Long id){
//        purchaseService.deleteById(id);
//        return "redirect:/admin/purchase/home"; //load controller url
//    }

}
