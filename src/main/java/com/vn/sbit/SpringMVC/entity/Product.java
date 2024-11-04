package com.vn.sbit.SpringMVC.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

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

    @Column(name = "product_name")
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
    private Category category;


}
