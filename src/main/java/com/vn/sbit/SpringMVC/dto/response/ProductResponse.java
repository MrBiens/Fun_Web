package com.vn.sbit.SpringMVC.dto.response;

import com.vn.sbit.SpringMVC.entity.Category;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Repository;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductResponse{
    private Long id;

    @NotBlank(message = "productName not null")
    private String productName;
    @NotBlank
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
