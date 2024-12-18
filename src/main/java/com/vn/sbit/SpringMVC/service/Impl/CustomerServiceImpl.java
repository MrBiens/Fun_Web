package com.vn.sbit.SpringMVC.service.Impl;

import com.vn.sbit.SpringMVC.dto.request.CustomerRequest;
import com.vn.sbit.SpringMVC.dto.response.CustomerResponse;
import com.vn.sbit.SpringMVC.entity.Customer;
import com.vn.sbit.SpringMVC.mapper.CustomerMapper;
import com.vn.sbit.SpringMVC.repository.CustomerRepository;
import com.vn.sbit.SpringMVC.service.CustomerService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class CustomerServiceImpl implements CustomerService {
    CustomerRepository customerRepository;
    CustomerMapper customerMapper;

    @Override
    public List<CustomerResponse> getAll() {
        return customerRepository.findAll().stream().map(customerMapper::toCustomerResponse).toList();
    }


    @Transactional
    @Override
    public CustomerResponse create(CustomerRequest request) {
        if(customerRepository.existsByFullName(request.getFullName())){
            throw new RuntimeException("Customer exists");
        }
        Customer customer =customerMapper.toCustomer(request);
        System.out.println(customer);
        customerRepository.save(customer);
        return customerMapper.toCustomerResponse(customer);
    }

    @Override
    public CustomerResponse updateById(Long id, Customer customer) {
        return null;
    }

    @Transactional
    @Override
    public void updateCustomer(Long id, CustomerRequest request) {
        if(customerRepository.existsByFullName(request.getFullName())){
            throw new RuntimeException("Customer FullName exists");
        }
        Customer customer = customerRepository.findById(id).orElseThrow(() ->  new RuntimeException("Cannot find Customer By ID"));
        customerMapper.updateCustomer(customer,request);
        System.out.println(customer);
        customerRepository.save(customer);
    }

    @Override
    public Customer findCustomerById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Cannot find Customer By Id"));
    }

    @Override
    public void deleteById(Long id) {
        if(customerRepository.existsById(id)){
            customerRepository.deleteById(id);
        }else{
            throw new RuntimeException("Cannot delete Customer By Id");
        }
    }



}
