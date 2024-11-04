package com.vn.sbit.SpringMVC.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/upload")
public class UploadFile {
    @GetMapping
    public String upload(){
        return "";
    }
}
