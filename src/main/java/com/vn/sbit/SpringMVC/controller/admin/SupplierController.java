package com.vn.sbit.SpringMVC.controller.admin;

import com.vn.sbit.SpringMVC.dto.request.supp.SupplierCreateRequest;
import com.vn.sbit.SpringMVC.dto.request.supp.SupplierUpdateRequest;
import com.vn.sbit.SpringMVC.dto.response.SupplierResponse;
import com.vn.sbit.SpringMVC.entity.Supplier;
import com.vn.sbit.SpringMVC.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
        SupplierCreateRequest request = new SupplierCreateRequest();
        model.addAttribute("supplier",request);
        return "admin/supplier/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("supplier") SupplierCreateRequest request,BindingResult result, Model model){
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

    @GetMapping("/edit/{id}")
    public String update(@PathVariable("id") Long id, Model model){
        Supplier supplier = supplierService.findById(id);
        model.addAttribute("id",supplier.getId());

        SupplierUpdateRequest request = new SupplierUpdateRequest();
        request.setSupplierName(supplier.getSupplierName());
        request.setAddress(supplier.getAddress());
        request.setNumberPhone(supplier.getNumberPhone());
        request.setEmail(supplier.getEmail());

        model.addAttribute("supplierUpdate",request);
        return "admin/supplier/edit";
    }

    @PostMapping("/edit")
    public String update(@RequestParam("id")Long id,@ModelAttribute("supplierUpdate")SupplierUpdateRequest request){
        if(request != null) {
            supplierService.updateById(id, request);
            return "redirect:/admin/supplier/home";
        }else {
            return "admin/supplier/edit";
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        supplierService.deleteById(id);
        return "redirect:/admin/supplier/home"; //load controller url
    }






}
