package com.vn.sbit.SpringMVC.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductRequest {


    @NotBlank(message = "productName not null")
    private String productName;
    @NotBlank
    private Double price;
    private String image;
    private String description;

    @NotBlank
    private Long categoryId;



}
