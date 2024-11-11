package com.vn.sbit.SpringMVC.service;

import com.vn.sbit.SpringMVC.dto.request.PurchaseDetailRequest;
import com.vn.sbit.SpringMVC.dto.response.PurchaseDetailResponse;
import com.vn.sbit.SpringMVC.dto.response.PurchaseResponse;
import com.vn.sbit.SpringMVC.entity.PurchaseInvoiceDetail;

public interface PurchaseDetailService extends CRUDService<PurchaseDetailRequest, PurchaseInvoiceDetail, PurchaseDetailResponse>{
}
