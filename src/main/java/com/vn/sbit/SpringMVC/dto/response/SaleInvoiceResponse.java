package com.vn.sbit.SpringMVC.dto.response;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SaleInvoiceResponse {
    Long id;

    String invoiceName;

    LocalDate dayCreate;

    CustomerResponse customer;

    EmployeeResponse employee;

    Double depot;

}
