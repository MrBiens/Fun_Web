package com.vn.sbit.SpringMVC.repository;

import com.vn.sbit.SpringMVC.entity.PurchaseInvoiceDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseDetailRepository extends JpaRepository<PurchaseInvoiceDetail,Long> {
    @Query("SELECT pd FROM PurchaseInvoiceDetail pd WHERE pd.purchaseInvoice.id = :purchaseInvoiceId")
    List<PurchaseInvoiceDetail> findPurchaseDetailsByPurchaseId(@Param("purchaseInvoiceId") Long purchaseInvoiceId);


}
