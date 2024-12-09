package com.vn.sbit.SpringMVC.dto.request;

import com.vn.sbit.SpringMVC.dto.response.ProductResponse;
import com.vn.sbit.SpringMVC.dto.response.SupplierResponse;
import lombok.*;

@AllArgsConstructor
@Getter
@Builder
public class ProductSupplierRequest {
    private ProductResponse productResponse;
    private SupplierResponse supplierResponse;
    private Double purchasePrice;

}
