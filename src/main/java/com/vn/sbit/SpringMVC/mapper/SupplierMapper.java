package com.vn.sbit.SpringMVC.mapper;

import com.vn.sbit.SpringMVC.dto.request.SupplierRequest;
import com.vn.sbit.SpringMVC.dto.response.SupplierResponse;
import com.vn.sbit.SpringMVC.entity.Supplier;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SupplierMapper {
//    @Mapping(target = "products", ignore = true) // Bỏ qua ánh xạ trường products
//    @Mapping(target = "purchaseInvoiceDetail", ignore = true) // Bỏ qua ánh xạ trường purchaseInvoiceDetail

    Supplier toSupplier(SupplierRequest request);

    SupplierResponse toSupplierResponse(Supplier supplier);

    void updateSupplier(@MappingTarget Supplier supplier, SupplierRequest request);
}
