package com.vn.sbit.SpringMVC.service.Impl;

import com.vn.sbit.SpringMVC.dto.request.EmployeeRequest;
import com.vn.sbit.SpringMVC.dto.response.EmployeeResponse;
import com.vn.sbit.SpringMVC.entity.Employee;
import com.vn.sbit.SpringMVC.mapper.EmployeeMapper;
import com.vn.sbit.SpringMVC.repository.EmployeeRepository;
import com.vn.sbit.SpringMVC.service.EmployeeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class EmployeeServiceImpl implements EmployeeService {
    EmployeeRepository employeeRepository;
    EmployeeMapper employeeMapper;

    @Override
    public List<EmployeeResponse> getAll() {
        return employeeRepository.findAll().stream().map(employeeMapper::toEmployeeResponse).toList();
    }
    @Transactional
    @Override
    public EmployeeResponse create(EmployeeRequest request) {
        Employee employee = employeeMapper.toEmployee(request);
        employeeRepository.save(employee);
        return employeeMapper.toEmployeeResponse(employee);
    }

    @Override
    public EmployeeResponse updateById(Long id, Employee employee) {
        return null;
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        if(employeeRepository.existsById(id)){
            employeeRepository.deleteById(id);
        }else{
            throw new RuntimeException("Cannot delete Employee because not find Id");
        }
    }

    @Override
    public Employee findById(Long id) {
        return employeeRepository.findById(id).orElseThrow( () -> new RuntimeException("Cannot find Employee"));
    }

    @Transactional
    @Override
    public void updateEmployee(Long id, EmployeeRequest request) {
        Employee employee = findById(id);
        employeeMapper.updateEmployee(employee,request);
        employeeRepository.save(employee);
    }
}
