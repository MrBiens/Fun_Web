package com.vn.sbit.SpringMVC.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Table(name = "sale_invoice")
@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@NoArgsConstructor
public class SaleInvoice {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name_invoice",unique = true)
    String invoiceName;

    @Column(name = "day_create")
    LocalDate dayCreate;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "customers_id")
    Customer customer;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "employees_id")
    Employee employee;

    @OneToMany(mappedBy = "saleInvoice",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    List<SaleInvoiceDetail> saleInvoiceDetails;

    @Column(name = "depot")
    String depot;
}
