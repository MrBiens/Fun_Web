package com.vn.sbit.SpringMVC.service;


import com.vn.sbit.SpringMVC.dto.request.purchase.PurchaseCreateRequest;
import com.vn.sbit.SpringMVC.dto.request.purchase.PurchaseUpdateRequest;
import com.vn.sbit.SpringMVC.dto.response.PurchaseResponse;
import com.vn.sbit.SpringMVC.entity.PurchaseInvoice;
import com.vn.sbit.SpringMVC.entity.Supplier;

public interface PurchaseService extends CRUDService<PurchaseCreateRequest, PurchaseUpdateRequest, PurchaseResponse>{
    public PurchaseInvoice findById(Long id);

}
