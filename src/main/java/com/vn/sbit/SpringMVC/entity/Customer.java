package com.vn.sbit.SpringMVC.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Table(name = "customers")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
@NoArgsConstructor
@Entity
public class Customer {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(name = "full_name")
    String fullName;

    @Column(name = "date_of_birth")
    String dateOfBirth;

    @Column(name = "email",unique = true)
    String email;

    @Column(name = "number_phone",length = 20,unique = true)
    String numberPhone;

    @OneToMany(mappedBy = "customer")
    List<SaleInvoice> saleInvoices;

}
