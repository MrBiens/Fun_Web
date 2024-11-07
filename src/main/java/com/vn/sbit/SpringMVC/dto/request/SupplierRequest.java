package com.vn.sbit.SpringMVC.dto.request;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SupplierRequest {
    String supplierName;

    String address;

    String numberPhone;

    String email;
}
