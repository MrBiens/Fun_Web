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
    private Double price;
    private String image;
    private String description;

    @NotBlank
    private Category category;

    @Override
    public String toString() {
        return "ProductResponse{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                ", category=" + category +
                '}';
    }
}
