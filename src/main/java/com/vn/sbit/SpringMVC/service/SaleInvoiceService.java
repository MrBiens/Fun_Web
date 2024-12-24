package com.vn.sbit.SpringMVC.service;

import com.vn.sbit.SpringMVC.dto.request.SaleInvoiceRequest;
import com.vn.sbit.SpringMVC.dto.response.SaleInvoiceResponse;
import com.vn.sbit.SpringMVC.entity.SaleInvoice;

public interface SaleInvoiceService extends CRUDService<SaleInvoiceRequest, SaleInvoice, SaleInvoiceResponse> {
    void updateSaleInvoice(Long id,SaleInvoiceRequest request);
    SaleInvoiceResponse findById(Long id);
    SaleInvoice findSaleInvoiceById(Long id);
}
