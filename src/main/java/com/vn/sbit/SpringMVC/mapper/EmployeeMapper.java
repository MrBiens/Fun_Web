package com.vn.sbit.SpringMVC.mapper;

import com.vn.sbit.SpringMVC.dto.request.EmployeeRequest;
import com.vn.sbit.SpringMVC.dto.response.EmployeeResponse;
import com.vn.sbit.SpringMVC.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    @Mapping(target = "fullName", source = "fullName")
    @Mapping(target = "dateOfBirth", source = "dateOfBirth")
    @Mapping(target = "address", source = "address")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "numberPhone", source = "numberPhone")
    Employee toEmployee(EmployeeRequest request);

    EmployeeResponse toEmployeeResponse(Employee employee);

    void updateEmployee(@MappingTarget Employee employee, EmployeeRequest request);
}
