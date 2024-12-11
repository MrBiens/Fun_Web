package com.vn.sbit.SpringMVC.controller.admin;

import com.vn.sbit.SpringMVC.dto.request.ProductRequest;
import com.vn.sbit.SpringMVC.dto.request.ProductSupplierRequest;
import com.vn.sbit.SpringMVC.dto.response.ProductResponse;
import com.vn.sbit.SpringMVC.dto.response.ProductSupplierResponse;
import com.vn.sbit.SpringMVC.dto.response.SupplierResponse;
import com.vn.sbit.SpringMVC.entity.Category;
import com.vn.sbit.SpringMVC.entity.Product;
import com.vn.sbit.SpringMVC.entity.ProductSupplier;
import com.vn.sbit.SpringMVC.service.*;
import com.vn.sbit.SpringMVC.service.Impl.ProductSupplierServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("/admin/productSupplier")
@Controller
public class ProductSupplierController {
    private static final Logger log = LoggerFactory.getLogger(ProductSupplierController.class);
    private final ProductSupplierService productSupplierService;
    private final ProductService productService;
    private final SupplierService supplierService;

    @Autowired
    public ProductSupplierController(ProductSupplierService productSupplierService, ProductService productService,  SupplierService supplierService) {
        this.productSupplierService = productSupplierService;
        this.productService=productService;
        this.supplierService=supplierService;
    }

    @GetMapping("/home")
    public String home(Model model){
        List<ProductSupplierResponse> list = productSupplierService.getAll();
        model.addAttribute("productSupplier_list",list);
        System.out.println(list);
        return "admin/productSupplier/index";
    }

    @GetMapping("/add")
    public String add(Model model){
        ProductSupplierRequest request = new ProductSupplierRequest();
        model.addAttribute("request",request);

        List<ProductResponse>  productResponseList = productService.getAll();
        model.addAttribute("product_list",productResponseList);

        List<SupplierResponse>  list_supplier = supplierService.getAll();
        model.addAttribute("list_supplier",list_supplier);

        return "admin/productSupplier/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("request")ProductSupplierRequest request) {
        ProductSupplierResponse productSupplierResponse = productSupplierService.create(request);
        if (productSupplierResponse != null) {
            return "redirect:/admin/productSupplier/home";
        } else {
            return "admin/productSupplier/add";
        }
    }



    @GetMapping("/edit/{id}")
    public String update(@PathVariable("id") Long id,Model model){
        ProductSupplier product=productSupplierService.findById(id);

        ProductSupplierRequest request =ProductSupplierRequest.builder()
                .id(product.getId())
                .purchasePrice(product.getPurchasePrice())
                .supplierId(product.getSupplier().getId())
                .productId(product.getProduct().getId())
                .build();

        model.addAttribute("request",request);

        List<ProductResponse>  productResponseList = productService.getAll();
        model.addAttribute("product_list",productResponseList);

        List<SupplierResponse>  list_supplier = supplierService.getAll();
        model.addAttribute("list_supplier",list_supplier);

        return "admin/productSupplier/edit";

    }


    @PostMapping("/edit/{id}")
    public String update(@ModelAttribute("request") ProductSupplierRequest request){
        if(productSupplierService.UpdateProductSupplier(request)){
            return "redirect:/admin/productSupplier/home";
        }else{
            return "admin/productSupplier/add";
        }
    }



    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        productSupplierService.deleteById(id);
        return "redirect:/admin/productSupplier/home"; //load controller url
    }




}
