package com.vn.sbit.SpringMVC.service;

import com.vn.sbit.SpringMVC.dto.request.PurchaseDetailRequest;
import com.vn.sbit.SpringMVC.dto.response.PurchaseDetailResponse;

import com.vn.sbit.SpringMVC.entity.PurchaseInvoiceDetail;

import java.util.List;

public interface PurchaseDetailService extends CRUDService<PurchaseDetailRequest, PurchaseInvoiceDetail, PurchaseDetailResponse> {
    public List<PurchaseDetailResponse> getPurchaseInvoiceDetailsByPurchaseInvoiceId(Long purchaseInvoiceId) ;
}
