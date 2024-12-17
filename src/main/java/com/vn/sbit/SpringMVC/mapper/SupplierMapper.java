package com.vn.sbit.SpringMVC.mapper;

import com.vn.sbit.SpringMVC.dto.request.supp.SupplierCreateRequest;
import com.vn.sbit.SpringMVC.dto.request.supp.SupplierUpdateRequest;
import com.vn.sbit.SpringMVC.dto.response.SupplierResponse;
import com.vn.sbit.SpringMVC.entity.Supplier;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SupplierMapper {
//    @Mapping(target = "products", ignore = true) // Bỏ qua ánh xạ trường products
//    @Mapping(target = "purchaseInvoiceDetail", ignore = true) // Bỏ qua ánh xạ trường purchaseInvoiceDetail
    Supplier toSupplier(SupplierCreateRequest request);

    SupplierResponse toSupplierResponse(Supplier supplier);

    @Mapping(target = "id",ignore = true)
    void updateSupplier(@MappingTarget Supplier supplier, SupplierUpdateRequest request);
}
