package com.vn.sbit.SpringMVC.dto.response;

import com.vn.sbit.SpringMVC.entity.ProductSupplier;
import com.vn.sbit.SpringMVC.entity.PurchaseInvoice;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PurchaseDetailResponse {
    Long id;

    PurchaseInvoice purchaseInvoice;

    ProductSupplier productSupplier;

    Long quantity;

    Double purchasePrice;

    Double totalPrice;

    Double debt;

}
