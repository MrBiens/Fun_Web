package com.vn.sbit.SpringMVC.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "suppliers")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "supplier_name",unique = true,columnDefinition = "VARCHAR(255) COLLATE utf8mb4_unicode_ci") //unique field - không phân biệt hoa thường (utf8mb4_bin là phân biệt)
    String supplierName;

    @Column(name = "address")
    String address;

    @Column(name = "number_phone")
    String numberPhone;

    @Column(name = "email")
    @Email
    String email;

    @Column(name = "debt")
    Double debt;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "supplier",cascade = {
            CascadeType.DETACH,
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH
    })
    List<PurchaseInvoice> purchaseInvoice;

//    @ManyToMany(fetch = FetchType.LAZY,cascade = {
//            CascadeType.PERSIST,
//            CascadeType.MERGE,
//            CascadeType.DETACH,
//            CascadeType.REFRESH
//    })
//    @JoinTable(name = "supplier_product"
//            ,joinColumns = @JoinColumn(name = "suppliers_id")
//            ,inverseJoinColumns = @JoinColumn(name = "products_id"))
//    List<Product> products;









}
