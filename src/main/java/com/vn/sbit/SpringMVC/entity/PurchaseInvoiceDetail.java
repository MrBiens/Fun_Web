package com.vn.sbit.SpringMVC.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

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
    @JoinColumn(name = "product_supplier_id")
    ProductSupplier productSupplier;

    @Column(name = "quantity")
    int quantity;

    @Column(name = "purchase_price")
    Double purchasePrice;

    @Column(name = "total_price")
    Double totalPrice;

    @Column(name = "total_quantity")
    int totalQuantity;

    @Column(name = "debt")
    Double debt;



    public PurchaseInvoiceDetail(Long id, PurchaseInvoice purchaseInvoice, ProductSupplier productSupplier, int quantity, Double purchasePrice, LocalDate importDate,Double debt) {
        this.id = id;
        this.purchaseInvoice = purchaseInvoice;
        this.productSupplier = productSupplier;
        this.quantity = quantity;
        this.purchasePrice = productSupplier.getPurchasePrice(); // Lấy giá từ Product khi khởi tạo
        this.totalPrice = quantity * purchasePrice; // Tính tổng tiền
        this.debt=debt;
    }

    public String getProductInfo(){
        return "Product Id:"+productSupplier.getProduct().getId()+", Product name:"+productSupplier.getProduct().getProductName();
    }










}
