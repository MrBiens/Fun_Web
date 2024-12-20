package com.vn.sbit.SpringMVC.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Entity
@Table(name = "purchase_invoice_detail")
@Getter
@Setter
@AllArgsConstructor
public class PurchaseInvoiceDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @ManyToOne(cascade ={
            CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST}
    )
    @JoinColumn(name = "purchase_invoice_id")
    PurchaseInvoice purchaseInvoice;


    @ManyToOne(cascade ={
            CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST}
    )
    @JoinColumn(name = "product_supplier_id")
    ProductSupplier productSupplier;

    @Column(name = "quantity")
    int quantity;

    @Column(name = "purchase_price")
    int purchasePrice;

    @Column(name = "total_price")
    int totalPrice;







}
