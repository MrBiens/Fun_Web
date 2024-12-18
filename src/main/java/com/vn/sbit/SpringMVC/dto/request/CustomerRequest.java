package com.vn.sbit.SpringMVC.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CustomerRequest {
    @NotBlank
    String fullName;

    @NotBlank
    String address;

    @Email
    String email;

    @NotBlank
    String numberPhone;

}
