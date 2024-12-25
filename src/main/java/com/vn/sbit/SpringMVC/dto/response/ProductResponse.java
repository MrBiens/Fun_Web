package com.vn.sbit.SpringMVC.dto.response;

import com.vn.sbit.SpringMVC.entity.Category;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProductResponse{
    private Long id;

    private String productName;
    private int quantity;
    private String image;
    private String description;

    @NotBlank
    private Category category;



    public ProductResponse(Long id, String productName) {
    }
}
