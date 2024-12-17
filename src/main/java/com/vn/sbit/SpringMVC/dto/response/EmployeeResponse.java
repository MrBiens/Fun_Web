package com.vn.sbit.SpringMVC.dto.response;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeResponse {
    Long id;

    @NotBlank
    String fullName;

    String dateOfBirth;
    
    @Email
    String email;

    @NotBlank
    String numberPhone;

}