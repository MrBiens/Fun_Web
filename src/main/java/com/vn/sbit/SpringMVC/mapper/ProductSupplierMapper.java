package com.vn.sbit.SpringMVC.mapper;

import com.vn.sbit.SpringMVC.dto.request.ProductRequest;
import com.vn.sbit.SpringMVC.dto.request.ProductSupplierRequest;
import com.vn.sbit.SpringMVC.dto.response.ProductResponse;
import com.vn.sbit.SpringMVC.dto.response.ProductSupplierResponse;
import com.vn.sbit.SpringMVC.entity.Product;
import com.vn.sbit.SpringMVC.entity.ProductSupplier;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductSupplierMapper {
    ProductSupplier toProductSupplier(ProductSupplierRequest request);

    ProductSupplierResponse toProductSupplierResponse(ProductSupplier productSupplier);

    void updateProductSupplier(@MappingTarget ProductSupplier product, ProductSupplierRequest request);
}
