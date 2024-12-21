package com.vn.sbit.SpringMVC.mapper;

import com.vn.sbit.SpringMVC.dto.request.SaleInvoiceDetailRequest;
import com.vn.sbit.SpringMVC.dto.response.SaleInvoiceDetailResponse;
import com.vn.sbit.SpringMVC.entity.SaleInvoiceDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SaleInvoiceDetailMapper {
    @Mapping(target = "quantity",source = "quantity")
    @Mapping(target = "price",source = "price")
    @Mapping(target = "totalPrice",source = "totalPrice")
    @Mapping(target = "saleInvoice",ignore = true)
    @Mapping(target = "product",ignore = true)
    SaleInvoiceDetail toSaleInvoiceDetail(SaleInvoiceDetailRequest request);

    SaleInvoiceDetailResponse toSaleInvoiceDetailResponse(SaleInvoiceDetail saleInvoiceDetail);

    @Mapping(target = "quantity",source = "quantity")
    @Mapping(target = "price",source = "price")
    @Mapping(target = "totalPrice",source = "totalPrice")
    @Mapping(target = "saleInvoice",ignore = true)
    @Mapping(target = "product",ignore = true)
    void updateSaleInvoiceDetail (@MappingTarget SaleInvoiceDetail saleInvoiceDetail,SaleInvoiceDetailRequest request);

}
