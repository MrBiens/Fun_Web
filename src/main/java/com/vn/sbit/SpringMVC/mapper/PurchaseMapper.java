package com.vn.sbit.SpringMVC.mapper;

import com.vn.sbit.SpringMVC.dto.request.ProductRequest;
import com.vn.sbit.SpringMVC.dto.request.purchase.PurchaseCreateRequest;
import com.vn.sbit.SpringMVC.dto.request.purchase.PurchaseUpdateRequest;
import com.vn.sbit.SpringMVC.dto.response.ProductResponse;
import com.vn.sbit.SpringMVC.dto.response.PurchaseResponse;
import com.vn.sbit.SpringMVC.entity.Product;
import com.vn.sbit.SpringMVC.entity.PurchaseInvoice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PurchaseMapper {
    @Mapping(target = "supplier", ignore = true) // Bỏ qua ánh xạ category
    PurchaseInvoice toPurchaseInvoice(PurchaseCreateRequest request);

    @Mapping(target = "id",source = "id")
    PurchaseResponse toPurchaseResponse(PurchaseInvoice purchase);

    void updatePurchase(@MappingTarget PurchaseInvoice purchase, PurchaseUpdateRequest request);
}
