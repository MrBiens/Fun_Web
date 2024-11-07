package com.vn.sbit.SpringMVC.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SupplierResponse {
    Long id;

    String supplierName;

    String address;

    String numberPhone;

    String email;

}
