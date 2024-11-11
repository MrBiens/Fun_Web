package com.vn.sbit.SpringMVC.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "product_name",unique = true,columnDefinition = "VARCHAR(255) COLLATE utf8mb4_unicode_ci")
    String productName;

    @Column(name = "price")
    Double price;

    @Column(name = "image")
    String image;

    @Column( name = "description")
    String description;


    @ManyToOne(cascade ={
            CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST})
    @JoinColumn(name = "category_id")
    Category category;

    @OneToMany(mappedBy = "product")
    private List<ProductSupplier> productSuppliers;


    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                ", category=" + category +
                '}';
    }
}
