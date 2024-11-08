package com.vn.sbit.SpringMVC.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "purchase_invoice_detail")
public class PurchaseInvoiceDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "purchase_invoice_id")
    PurchaseInvoice purchaseInvoice;


    @ManyToOne(cascade ={
            CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST}
    )
    @JoinColumn(name = "products_id")
    Product product;

    @Column(name = "quantity")
    int quantity;

    @Column(name = "purchase_price")
    Double purchasePrice;

    @Column(name = "total_price")
    Double totalPrice;

    @Column(name = "import_date")
    LocalDate importDate;


    public Double calculateTotalPrice() {
        return quantity * purchasePrice;
    }







}
