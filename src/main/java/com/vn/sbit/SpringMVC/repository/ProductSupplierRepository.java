package com.vn.sbit.SpringMVC.repository;

import com.vn.sbit.SpringMVC.entity.ProductSupplier;
import com.vn.sbit.SpringMVC.entity.PurchaseInvoiceDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductSupplierRepository extends JpaRepository<ProductSupplier, Long> {

    @Query("SELECT ps FROM ProductSupplier ps WHERE ps.supplier.id = :supplierId")
    List<ProductSupplier> findProductSupplierBySupplierId(@Param("supplierId") Long supplierId);

}