package com.vn.sbit.SpringMVC.service;


import com.vn.sbit.SpringMVC.dto.request.purchase.PurchaseCreateRequest;
import com.vn.sbit.SpringMVC.dto.request.purchase.PurchaseUpdateRequest;
import com.vn.sbit.SpringMVC.dto.response.PurchaseResponse;

public interface PurchaseService extends CRUDService<PurchaseCreateRequest, PurchaseUpdateRequest, PurchaseResponse>{
}
