package com.vn.sbit.SpringMVC.dto.response;

import com.vn.sbit.SpringMVC.entity.Supplier;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PurchaseResponse {
    Long id;
    String purchaseInvoiceName;
    Supplier supplier;
    LocalDate importDate;
    int totalAmount;
}
