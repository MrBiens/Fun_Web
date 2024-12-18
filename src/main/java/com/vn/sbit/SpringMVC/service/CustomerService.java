package com.vn.sbit.SpringMVC.service;

import com.vn.sbit.SpringMVC.dto.request.CustomerRequest;
import com.vn.sbit.SpringMVC.dto.response.CustomerResponse;
import com.vn.sbit.SpringMVC.entity.Customer;

public interface CustomerService extends CRUDService<CustomerRequest, Customer, CustomerResponse>{
    void updateCustomer(Long id,CustomerRequest request);
    Customer findCustomerById(Long id);
}
