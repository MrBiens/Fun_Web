package com.vn.sbit.SpringMVC.repository;

import com.vn.sbit.SpringMVC.entity.PurchaseInvoiceDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseDetailRepository extends JpaRepository<PurchaseInvoiceDetail,Long> {
    List<PurchaseInvoiceDetail> findByPurchaseInvoiceId(Long purchaseInvoiceId);

}
