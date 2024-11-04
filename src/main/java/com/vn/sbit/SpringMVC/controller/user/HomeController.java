package com.vn.sbit.SpringMVC.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping()
    public String showHomePage(){
        return "home/index";
    }


}