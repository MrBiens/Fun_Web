package com.vn.sbit.SpringMVC.service;

import com.vn.sbit.SpringMVC.dto.request.purchaseDetail.PurchaseDetailRequest;
import com.vn.sbit.SpringMVC.dto.request.purchaseDetail.PurchaseDetailUpdateRequest;
import com.vn.sbit.SpringMVC.dto.response.PurchaseDetailResponse;

import com.vn.sbit.SpringMVC.entity.PurchaseInvoiceDetail;

import java.util.List;

public interface PurchaseDetailService extends CRUDService<PurchaseDetailRequest, PurchaseDetailUpdateRequest, PurchaseDetailResponse> {
    public List<PurchaseDetailResponse> getPurchaseInvoiceDetailsByPurchaseInvoiceId(Long purchaseInvoiceId) ;
    public Double calculateTotalAmountByInvoiceId(Long purchaseInvoiceId);
    public PurchaseInvoiceDetail getById(Long purchaseDetailId);

    public Integer calculateTotalQuantityByInvoiceId(Long purchaseInvoiceId);
}
