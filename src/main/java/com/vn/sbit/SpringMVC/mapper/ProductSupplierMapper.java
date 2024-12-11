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
    @Mapping(target = "product.id", source = "productId")
    @Mapping(target = "supplier.id", source = "supplierId")
    @Mapping(target = "id",ignore = true)
    ProductSupplier toProductSupplier(ProductSupplierRequest request);

    @Mapping(source = "product", target = "productResponse")
    @Mapping(source = "supplier", target = "supplierResponse")
    ProductSupplierResponse toProductSupplierResponse(ProductSupplier productSupplier);

    void updateProductSupplier(@MappingTarget ProductSupplier product, ProductSupplierRequest request);
}
