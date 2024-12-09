package com.vn.sbit.SpringMVC.controller.admin;

import com.vn.sbit.SpringMVC.dto.request.ProductRequest;
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
    private final CategoryService categoryService;
    private final StorageService storageService;
    private final SupplierService supplierService;

    @Autowired
    public ProductSupplierController(ProductSupplierService productSupplierService, CategoryService categoryService, StorageService storageService, SupplierService supplierService) {
        this.productSupplierService = productSupplierService;
        this.categoryService=categoryService;
        this.storageService=storageService;
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
        ProductRequest request = new ProductRequest();
        model.addAttribute("request",request);

        List<Category>  categoryList = categoryService.getAll();
        model.addAttribute("category_list",categoryList);

        List<SupplierResponse>  list_supplier = supplierService.getAll();
        model.addAttribute("list_supplier",list_supplier);

        return "admin/product/add";
    }

//    @PostMapping("/add")
//    public String add(@ModelAttribute("request") ProductRequest request, BindingResult result, Model model,
//                      @RequestParam("file") MultipartFile file
//                      ){
//
//        if (result.hasErrors()) {
//            model.addAttribute("message_error", "File không hợp lệ, vui lòng chọn một file.");
//            return "redirect:/admin/product/add"; // Trả về trang thêm sản phẩm
//        }
//
//        storageService.store(file);
//        String fileName = file.getOriginalFilename(); //ten chu khong co dinh dang
//        request.setImage(fileName);
//
//        ProductResponse response = productService.createProduct(request);
//        if(response != null){
//            model.addAttribute("response",response);
//            return "redirect:/admin/product/home";
//        }else{
//            return "admin/product/add";
//        }
//    }
//    @GetMapping("/edit/{id}")
//    public String update(@PathVariable("id") Long id,Model model){
//        Product product=productService.findById(id);
//        model.addAttribute("product",product);
//
//        List<Category>  categoryList = categoryService.getAll();
//        model.addAttribute("category_list",categoryList);
//        return "admin/product/edit";
//
//    }
//
//    @PostMapping("/edit/{id}")
//    public String update(@PathVariable("id") Long id,@ModelAttribute("product") ProductRequest request,@RequestParam("file") MultipartFile file
//            ,@RequestParam("category_product")Category category){
//        // request thiếu category Id -> Vì là Object Category
//        if(request!=null){
//            request.setCategoryId(category.getId()); // ProductRequest category = id ,Product category = long
//            storageService.store(file);
//            String fileName= file.getOriginalFilename();
//
//            request.setImage(fileName);
//            productService.updateProduct(id,request);
//            return "redirect:/admin/product/home"; //load controller url
//        }else{
//            return "admin/product/edit";
//        }
//    }
//
//    @GetMapping("/delete/{id}")
//    public String delete(@PathVariable("id") Long id){
//        productService.deleteProduct(id);
//        return "redirect:/admin/product/home"; //load controller url
//    }





}