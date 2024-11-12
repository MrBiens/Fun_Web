package com.vn.sbit.SpringMVC.dto.response;

import com.vn.sbit.SpringMVC.entity.ProductSupplier;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PurchaseDetailResponse {
    Long id;
    ProductSupplier productSupplier;
    int quantity;
    Double purchasePrice;
    Double totalPrice;

}
