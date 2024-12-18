package com.vn.sbit.SpringMVC.controller.admin;

import com.vn.sbit.SpringMVC.dto.request.CustomerRequest;
import com.vn.sbit.SpringMVC.dto.response.CustomerResponse;
import com.vn.sbit.SpringMVC.entity.Customer;
import com.vn.sbit.SpringMVC.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/customer")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/home")
    public String home(Model model){
        List<CustomerResponse> customerResponseList = customerService.getAll();
        model.addAttribute("customer_list",customerResponseList);
        return "admin/customer/index";
    }

    @GetMapping("/add")
    public String add(Model model){
        CustomerRequest request = new CustomerRequest();
        model.addAttribute("request",request);
        return "admin/customer/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("request") CustomerRequest request){
        if(customerService.create(request) != null){
            return "redirect:/admin/customer/home";
        }else{
            return "admin/customer/add";
        }
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id,Model model){
        model.addAttribute("id",id);
        Customer customer=customerService.findCustomerById(id);
        CustomerRequest request = CustomerRequest.builder()
                .fullName(customer.getFullName())
                .address(customer.getAddress())
                .email(customer.getEmail())
                .numberPhone(customer.getNumberPhone())
                .build();
        model.addAttribute("request",request);
        return "admin/customer/edit";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute("request") CustomerRequest request,@RequestParam("id")Long id){
        customerService.updateCustomer(id,request);
        return "redirect:/admin/customer/home";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id")Long id){
        customerService.deleteById(id);
        return "redirect:/admin/customer/home";
    }



}

