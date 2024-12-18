package com.vn.sbit.SpringMVC.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Table(name = "customers")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Customer {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "full_name")
    String fullName;

    @Column(name = "address")
    String address;

    @Column(name = "email",unique = true)
    String email;

    @Column(name = "number_phone",length = 20,unique = true)
    String numberPhone;

    @OneToMany(mappedBy = "customer")
    List<SaleInvoice> saleInvoices;

}
