package com.vn.sbit.SpringMVC.dto.response;

import com.vn.sbit.SpringMVC.entity.Category;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.stereotype.Repository;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductResponse{
    private Long id;

    private String productName;
    private Long quantity;;
    private String image;
    private String description;

    @NotBlank
    private Category category;


}
