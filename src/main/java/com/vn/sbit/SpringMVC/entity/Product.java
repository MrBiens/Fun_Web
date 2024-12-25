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
@ToString
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "product_name",unique = true,columnDefinition = "VARCHAR(255) COLLATE utf8mb4_unicode_ci")
    String productName;

    @Column(name = "quantity")
    int quantity;

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

    @OneToMany(mappedBy = "product"
            ,cascade = {CascadeType.ALL
    })
    List<ProductSupplier> productSuppliers;

    @OneToMany(cascade = {
            CascadeType.ALL
    },mappedBy = "product")
    List<SaleInvoiceDetail> saleInvoicesDetails;



}
