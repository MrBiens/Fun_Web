package com.vn.sbit.SpringMVC.controller.user;

import com.vn.sbit.SpringMVC.dto.response.ProductResponse;
import com.vn.sbit.SpringMVC.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    private final ProductService productService;

    @Autowired
    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public String showHomePage(Model model){
        List<ProductResponse> list = productService.getAll();
        model.addAttribute("product_list_forYou",list);
        return "home/index";
    }


}
