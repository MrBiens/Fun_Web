package com.vn.sbit.SpringMVC.controller.admin;

import com.vn.sbit.SpringMVC.dto.request.ProductRequest;
import com.vn.sbit.SpringMVC.dto.response.ProductResponse;
import com.vn.sbit.SpringMVC.entity.Category;
import com.vn.sbit.SpringMVC.entity.Product;
import com.vn.sbit.SpringMVC.service.CategoryService;
import com.vn.sbit.SpringMVC.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/admin/product")
@Controller
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;

    @Autowired
    public ProductController(ProductService productService,CategoryService categoryService) {
        this.productService = productService;
        this.categoryService=categoryService;
    }


    @GetMapping("/home")
    public String home(Model model){
        List<Product> list = productService.getAll();
        model.addAttribute("product_list",list);

        return "admin/product/index";
    }

    @GetMapping("/add")
    public String add(Model model){
        ProductRequest request = new ProductRequest();
        model.addAttribute("request",request);

        List<Category>  categoryList = categoryService.getAll();
        model.addAttribute("category_list",categoryList);

        return "admin/product/add";
    }


}
