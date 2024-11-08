package com.vn.sbit.SpringMVC.dto.request.supp;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SupplierCreateRequest {
        @NotBlank(message = "Supplier Name not null")
        String supplierName;

        String address;

        String numberPhone;

        String email;


}
