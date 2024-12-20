package com.vn.sbit.SpringMVC.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductRequest {
    private String productName;
    private int quantity;
    private String image;
    private String description;
    private Long categoryId;


}
