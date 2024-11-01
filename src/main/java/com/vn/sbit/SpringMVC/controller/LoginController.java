package com.vn.sbit.SpringMVC.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/proviso")
public class LoginController {

    @GetMapping("/login")
    public String showLoginPage(){
        return "login";
    }

    @GetMapping("/show403Page")
    public String show403Page(){
        return "error/403";
    }
}
