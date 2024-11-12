package com.vn.sbit.SpringMVC.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseDetailRequest {
    Long purchaseInvoiceId;
    Long productSupplierId;
    int quantity;
    Double purchasePrice;
}
