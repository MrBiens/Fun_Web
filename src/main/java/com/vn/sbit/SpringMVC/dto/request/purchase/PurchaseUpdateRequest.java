package com.vn.sbit.SpringMVC.dto.request.purchase;

import com.vn.sbit.SpringMVC.entity.Supplier;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class PurchaseUpdateRequest {
    String purchaseInvoiceName;

    Long supplierId;
}
