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
    @Mapping(target = "totalPrice",ignore = true)
    @Mapping(target = "saleInvoice",ignore = true)
    @Mapping(target = "product",ignore = true)
    SaleInvoiceDetail toSaleInvoiceDetail(SaleInvoiceDetailRequest request);

    @Mapping(target = "id",source = "id")
    @Mapping(target = "saleInvoiceResponse",source = "saleInvoice")
    @Mapping(target = "productResponse",source = "product")
    @Mapping(target = "price",source = "price")
    @Mapping(target = "quantity",source = "quantity")
    @Mapping(target = "totalPrice",source = "totalPrice")
    SaleInvoiceDetailResponse toSaleInvoiceDetailResponse(SaleInvoiceDetail saleInvoiceDetail);

    @Mapping(target = "quantity",source = "quantity")
    @Mapping(target = "price",source = "price")
    @Mapping(target = "totalPrice",ignore = true)
    @Mapping(target = "saleInvoice",ignore = true)
    @Mapping(target = "product",ignore = true)
    void updateSaleInvoiceDetail (@MappingTarget SaleInvoiceDetail saleInvoiceDetail,SaleInvoiceDetailRequest request);

}
