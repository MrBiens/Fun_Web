package com.vn.sbit.SpringMVC.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SupplierRequest {
    @NotBlank(message = "Supplier Name not null")
    String supplierName;

    String address;

    String numberPhone;

    String email;
}
