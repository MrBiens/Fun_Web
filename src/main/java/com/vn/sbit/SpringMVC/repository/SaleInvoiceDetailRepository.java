package com.vn.sbit.SpringMVC.repository;

import com.vn.sbit.SpringMVC.entity.PurchaseInvoiceDetail;
import com.vn.sbit.SpringMVC.entity.SaleInvoiceDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleInvoiceDetailRepository extends JpaRepository<SaleInvoiceDetail,Long> {
    @Query("SELECT sd FROM SaleInvoiceDetail sd where sd.saleInvoice.id = :saleInvoiceId") // :phai lien voi param
    List<SaleInvoiceDetail> findSaleInvoiceDetailBySaleInvoiceId(@Param("saleInvoiceId")Long saleInvoiceId);

//    @Query("SELECT pd FROM PurchaseInvoiceDetail pd WHERE pd.purchaseInvoice.id = :purchaseInvoiceId")
//    List<PurchaseInvoiceDetail> findPurchaseDetailsByPurchaseId(@Param("purchaseInvoiceId") Long purchaseInvoiceId);

}

