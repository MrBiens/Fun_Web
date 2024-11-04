package com.vn.sbit.SpringMVC.controller;

import com.vn.sbit.SpringMVC.service.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/upload-test")
public class TestUpload {

    private static final Logger log = LoggerFactory.getLogger(TestUpload.class);
    @Autowired
    private StorageService storageService;

    @GetMapping("/home")
    public String upload(){
        return "test/test_upload";
    }

    @PostMapping("/save")
    public String save(@RequestParam("file")MultipartFile file){
        storageService.store(file);
        String filename= file.getOriginalFilename();
        log.info("File name:"+filename);
        return "test/test_upload";
    }



}
