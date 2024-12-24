package com.vn.sbit.SpringMVC.dto.request;

import jakarta.validation.constraints.Min;
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

    @Min(value = 1,message = "QUANTITY_NOT_POSITIVE_NUMBER")
    int quantity;

    @Min(value = 1,message = "PRICE_NOT_POSITIVE_NUMBER")
    int price;




}
