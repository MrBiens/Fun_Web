package com.vn.sbit.SpringMVC.repository;

import com.vn.sbit.SpringMVC.entity.PurchaseInvoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends JpaRepository<PurchaseInvoice,Long> {
    boolean existsByPurchaseInvoiceName(String supplierName);

}
