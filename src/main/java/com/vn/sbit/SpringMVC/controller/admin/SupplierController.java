package com.vn.sbit.SpringMVC.controller.admin;

import com.vn.sbit.SpringMVC.dto.request.SupplierRequest;
import com.vn.sbit.SpringMVC.dto.response.SupplierResponse;
import com.vn.sbit.SpringMVC.service.SupplierService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/supplier")
public class SupplierController {
    private final SupplierService supplierService;
    @Autowired
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping("/home")
    public String index(Model model){
        List<SupplierResponse> list_supplier=supplierService.getAll();
        model.addAttribute("list_supplier",list_supplier);
        return "admin/supplier/index";
    }

    @GetMapping("/add")
    public String add(Model model){
        SupplierRequest request = new SupplierRequest();
        model.addAttribute("supplier",request);
        return "admin/supplier/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("supplier") SupplierRequest request,BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("message_error","Tên sản phẩm đã tồn tại");
            return "redirect:/admin/supplier/add";
        }
        if(request != null){
            SupplierResponse response=supplierService.create(request);
            return "redirect:/admin/supplier/home";
        }else{
            return "admin/supplier/add";
        }
    }


}
