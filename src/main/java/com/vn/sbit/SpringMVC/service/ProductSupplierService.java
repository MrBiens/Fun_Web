package com.vn.sbit.SpringMVC.service;

import com.vn.sbit.SpringMVC.dto.request.ProductSupplierRequest;
import com.vn.sbit.SpringMVC.dto.response.ProductSupplierResponse;
import com.vn.sbit.SpringMVC.entity.ProductSupplier;

import java.util.List;

public interface ProductSupplierService extends CRUDService<ProductSupplierRequest,ProductSupplier, ProductSupplierResponse> {
}
