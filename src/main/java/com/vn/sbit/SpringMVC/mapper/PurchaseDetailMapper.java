package com.vn.sbit.SpringMVC.mapper;

import com.vn.sbit.SpringMVC.dto.request.purchaseDetail.PurchaseDetailRequest;
import com.vn.sbit.SpringMVC.dto.request.purchaseDetail.PurchaseDetailUpdateRequest;
import com.vn.sbit.SpringMVC.dto.response.PurchaseDetailResponse;
import com.vn.sbit.SpringMVC.entity.PurchaseInvoiceDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PurchaseDetailMapper {
    @Mapping(target = "productSupplier", ignore = true) // Bỏ qua ánh xạ product
    @Mapping(target = "purchaseInvoice",ignore = true)
    PurchaseInvoiceDetail toPurchaseInvoiceDetail(PurchaseDetailRequest request);

    @Mapping(target = "id",source = "id")
    @Mapping(target = "purchaseInvoice",source = "purchaseInvoice")
    PurchaseDetailResponse toPurchaseDetailResponse(PurchaseInvoiceDetail purchaseDetail);

    @Mapping(target = "productSupplier", ignore = true) // Bỏ qua ánh xạ productSupplier
    @Mapping(target = "purchaseInvoice",ignore = true)
    void updatePurchaseDetail (@MappingTarget PurchaseInvoiceDetail purchaseDetail, PurchaseDetailUpdateRequest request);
}
