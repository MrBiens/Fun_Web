package com.vn.sbit.SpringMVC.mapper;

import com.vn.sbit.SpringMVC.dto.request.CustomerRequest;
import com.vn.sbit.SpringMVC.dto.response.CustomerResponse;
import com.vn.sbit.SpringMVC.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    @Mapping(target = "fullName",source = "fullName")
    @Mapping(target = "address",source = "address")
    @Mapping(target = "email",source = "email")
    @Mapping(target = "numberPhone",source = "numberPhone")
    Customer toCustomer(CustomerRequest request);

    CustomerResponse toCustomerResponse(Customer customer);

    void updateCustomer(@MappingTarget Customer customer, CustomerRequest request);
}
