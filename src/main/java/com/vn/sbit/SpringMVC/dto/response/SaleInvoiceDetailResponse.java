package com.vn.sbit.SpringMVC.dto.response;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SaleInvoiceDetailResponse {
    Long id;

    SaleInvoiceResponse saleInvoiceResponse;

    ProductResponse productResponse;

    int quantity;

    int price;

    int totalPrice;

}
