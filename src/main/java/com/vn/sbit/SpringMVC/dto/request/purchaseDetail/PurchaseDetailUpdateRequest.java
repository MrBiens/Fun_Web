package com.vn.sbit.SpringMVC.dto.request.purchaseDetail;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseDetailUpdateRequest {
    Long purchaseInvoiceId;
    Long productSupplierId;
    Long quantity;
    Double purchasePrice;
}

