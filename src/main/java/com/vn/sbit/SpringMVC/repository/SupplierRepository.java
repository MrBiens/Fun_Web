package com.vn.sbit.SpringMVC.repository;

import com.vn.sbit.SpringMVC.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier,Long> {
    boolean existsBySupplierName(String supplierName);


}
