package com.vn.sbit.SpringMVC.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/supplier")
public class SupplierController {

    @GetMapping("/home")
    public String index(){
        return "admin/supplier/index";
    }
}
