package com.vn.sbit.SpringMVC.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Table(name = "employees")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
//@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(name = "full_name",unique = true)
    String fullName;

    @Column(name = "address")
    String address;

    @Column(name = "date_of_birth")
    String dateOfBirth;


    @Column(name = "email",unique = true)
    String email;

    @Column(name = "number_phone",unique = true)
    String numberPhone;

    @OneToMany(mappedBy = "employee")
    List<SaleInvoice> saleInvoices;



}
