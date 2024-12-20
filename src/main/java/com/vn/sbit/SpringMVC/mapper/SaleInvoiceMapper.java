package com.vn.sbit.SpringMVC.mapper;

import com.vn.sbit.SpringMVC.dto.request.SaleInvoiceRequest;
import com.vn.sbit.SpringMVC.dto.response.SaleInvoiceResponse;
import com.vn.sbit.SpringMVC.entity.SaleInvoice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SaleInvoiceMapper {
    @Mapping(target = "customer",ignore = true)
    @Mapping(target = "employee",ignore = true)
    SaleInvoice toSaleInvoice(SaleInvoiceRequest request);

    SaleInvoiceResponse toSaleInvoiceResponse(SaleInvoice saleInvoice);

    @Mapping(target = "customer",ignore = true)
    @Mapping(target = "employee",ignore = true)
    void updateSaleInvoice(@MappingTarget SaleInvoice saleInvoice,SaleInvoiceRequest request);

}
