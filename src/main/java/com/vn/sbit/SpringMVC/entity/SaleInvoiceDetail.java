package com.vn.sbit.SpringMVC.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Table(name = "sale_invoice_detail")
@AllArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Entity
@Setter
public class SaleInvoiceDetail {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(cascade = {
            CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH
    })
    @JoinColumn(name ="sale_invoice_id")
    SaleInvoice saleInvoice;

    @ManyToOne(cascade = {
            CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST
    })
    @JoinColumn(name = "product_id")
    Product product;

    @Column(name = "quantity")
    int quantity;

    @Column(name = "price",length = 10)
    int price;

    @Column(name = "total_price",length = 12)
    int totalPrice;

}
