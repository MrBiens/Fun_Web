package com.vn.sbit.SpringMVC.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Table(name = "sale_invoice_detail")
@AllArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Entity
public class SaleInvoiceDetail {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(cascade = {
            CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH
    })
    @JoinColumn(name ="saleInvoice_id")
    SaleInvoice saleInvoice;

    @ManyToOne(cascade = {
            CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST
    })
    @JoinColumn(name = "product_id")
    Product product;

    @Column(name = "quantity")
    Long quantity;

    @Column(name = "price")
    float price;

    @Column(name = "total_price")
    Double totalPrice;


}
