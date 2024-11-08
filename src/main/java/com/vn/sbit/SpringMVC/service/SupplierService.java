package com.vn.sbit.SpringMVC.service;

import com.vn.sbit.SpringMVC.dto.request.supp.SupplierCreateRequest;
import com.vn.sbit.SpringMVC.dto.request.supp.SupplierUpdateRequest;
import com.vn.sbit.SpringMVC.dto.response.SupplierResponse;
import com.vn.sbit.SpringMVC.entity.Supplier;

public interface SupplierService extends CRUDService<Supplier, SupplierCreateRequest, SupplierUpdateRequest, SupplierResponse> {
    public Supplier findById(Long id);

}
