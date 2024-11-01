package com.vn.sbit.SpringMVC.controller;

import com.vn.sbit.SpringMVC.entity.User;
import com.vn.sbit.SpringMVC.service.Impl.UserServiceImpl;
import com.vn.sbit.SpringMVC.dto.request.RegisterUser;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/register")
public class RegisterController {
    UserServiceImpl userService;

    @Autowired
    public RegisterController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/showRegister")
    public String showRegister(Model model){
        RegisterUser registerUser = new RegisterUser();
        model.addAttribute("register",registerUser);
        return "register/register";
    }
//    @InitBinder
//    public void initBinder(WebDataBinder webDataBinder){
//        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
//        webDataBinder.registerCustomEditor(String.class,stringTrimmerEditor);
//    }

    @PostMapping("/process")
    public String process(@Valid @ModelAttribute("register") RegisterUser registerUser, BindingResult result, Model model, HttpSession session){
        String username=registerUser.getUsername();
        if(result.hasErrors()){
          return "register/register";
        }
        User user = userService.findByUsername(username);
        if(user != null){
            model.addAttribute("register",new RegisterUser());
            model.addAttribute("message_error","Username exist");
            return "register/register";
        }else {
            userService.createUser(registerUser);
            session.setAttribute("register_user", user);
            return "home/index";
        }
    }


}
