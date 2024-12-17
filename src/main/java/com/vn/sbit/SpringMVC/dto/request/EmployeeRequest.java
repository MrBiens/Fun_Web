package com.vn.sbit.SpringMVC.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@AllArgsConstructor
public class EmployeeRequest {
    @NotBlank
    String fullName;

    String dateOfBirth;
    
    @Email
    String email;

    @NotBlank
    String numberPhone;

}
