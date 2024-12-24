package com.vn.sbit.SpringMVC.service;

import com.vn.sbit.SpringMVC.dto.request.SaleInvoiceDetailRequest;
import com.vn.sbit.SpringMVC.dto.response.SaleInvoiceDetailResponse;
import com.vn.sbit.SpringMVC.entity.SaleInvoiceDetail;

import java.util.List;

public interface SaleInvoiceDetailService extends CRUDService<SaleInvoiceDetailRequest, SaleInvoiceDetail, SaleInvoiceDetailResponse>{
    List<SaleInvoiceDetailResponse> findBySaleInvoiceId(Long SaleInvoiceId);
    void updateSaleInvoiceDetail(Long id,SaleInvoiceDetailRequest request);
    SaleInvoiceDetailResponse findSaleInvoiceDetailBySaleId(Long id);
}
