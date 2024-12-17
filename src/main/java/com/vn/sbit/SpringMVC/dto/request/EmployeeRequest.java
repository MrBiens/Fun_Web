package com.vn.sbit.SpringMVC.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
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
