package com.vn.sbit.SpringMVC.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequest {
    @NotBlank
    String fullName;

    String dateOfBirth;

    String address;

    @Email
    String email;

    @NotBlank
    String numberPhone;

}
