package com.vn.sbit.SpringMVC.repository;

import com.vn.sbit.SpringMVC.entity.ProductSupplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductSupplierRepository extends JpaRepository<ProductSupplier, Long> {
}