package com.vn.sbit.SpringMVC.repository;

import com.vn.sbit.SpringMVC.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
