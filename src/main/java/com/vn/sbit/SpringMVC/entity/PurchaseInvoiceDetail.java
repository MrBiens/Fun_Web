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
    Long quantity;

    @Column(name = "purchase_price")
    Double purchasePrice;

    @Column(name = "total_price")
    Double totalPrice;

    @Column(name = "debt")
    Double debt;


    public PurchaseInvoiceDetail(Long id, PurchaseInvoice purchaseInvoice, ProductSupplier productSupplier, Long quantity, Double purchasePrice, LocalDate importDate,Double debt) {
        this.id = id;
        this.purchaseInvoice = purchaseInvoice;
        this.productSupplier = productSupplier;
        this.quantity = quantity;
        this.purchasePrice = productSupplier.getPurchasePrice(); // Lấy giá từ Product khi khởi tạo
        this.totalPrice = quantity * purchasePrice; // Tính tổng tiền
        this.debt=debt;
    }



}
