package com.vn.sbit.SpringMVC.controller.admin;

import com.vn.sbit.SpringMVC.dto.request.EmployeeRequest;
import com.vn.sbit.SpringMVC.dto.request.ProductRequest;
import com.vn.sbit.SpringMVC.dto.response.EmployeeResponse;
import com.vn.sbit.SpringMVC.dto.response.SupplierResponse;
import com.vn.sbit.SpringMVC.entity.Category;
import com.vn.sbit.SpringMVC.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/home")
    public String home(Model model){
        List<EmployeeResponse> employeeResponseList = employeeService.getAll();
        model.addAttribute("employee_list",employeeResponseList);
        return "admin/employee/index";
    }

    @GetMapping("/add")
    public String add(Model model){
        EmployeeRequest request = new EmployeeRequest();
        model.addAttribute("request",request);
        return "admin/employee/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("request") EmployeeRequest request){
        if(employeeService.create(request) != null){
            return "redirect:/admin/employee/home";
        }else{
            return "admin/employee/add";
        }
    }



}
