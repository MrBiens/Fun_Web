package com.vn.sbit.SpringMVC.dto.request.purchaseDetail;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseDetailRequest {
    Long purchaseInvoiceId;
    Long productSupplierId;
    int quantity;
    int purchasePrice;
}
