package com.vn.sbit.SpringMVC.entity;

import com.vn.sbit.SpringMVC.enum_project.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Table(name = "sale_invoice")
@Getter
@Setter
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

    @Column(name = "total_amount",length = 10)
    int totalAmount ;

}
