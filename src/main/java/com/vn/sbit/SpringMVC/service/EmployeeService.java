package com.vn.sbit.SpringMVC.service;

import com.vn.sbit.SpringMVC.dto.request.EmployeeRequest;
import com.vn.sbit.SpringMVC.dto.response.EmployeeResponse;
import com.vn.sbit.SpringMVC.entity.Employee;

import java.util.Optional;

public interface EmployeeService extends CRUDService<EmployeeRequest, Employee, EmployeeResponse>{
    Employee findById(Long id);
    void updateEmployee(Long id,EmployeeRequest request);
}
