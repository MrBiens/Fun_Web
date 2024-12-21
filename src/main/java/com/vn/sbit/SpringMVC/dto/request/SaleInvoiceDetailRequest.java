package com.vn.sbit.SpringMVC.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SaleInvoiceDetailRequest {
    @NotNull
    Long saleInvoiceId;

    @NotNull
    Long productId;

    @NotBlank
    int quantity;

    @NotBlank
    int price;

    int totalPrice;



}
