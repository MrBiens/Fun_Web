package com.vn.sbit.SpringMVC.dto.request;

import com.vn.sbit.SpringMVC.dto.response.ProductResponse;
import com.vn.sbit.SpringMVC.dto.response.SupplierResponse;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString
public class ProductSupplierRequest {
    private Long productId;
    private Long supplierId;
    private Double purchasePrice;


}
