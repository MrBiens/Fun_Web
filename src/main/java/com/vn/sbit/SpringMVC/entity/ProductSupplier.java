package com.vn.sbit.SpringMVC.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "product_supplier")
@Setter
@Getter
public class ProductSupplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)
    private Supplier supplier;

    private Double purchasePrice; // Giá của sản phẩm từ nhà cung cấp

    @OneToMany(fetch = FetchType.LAZY,cascade ={
            CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST}
            ,mappedBy = "productSupplier")
    List<PurchaseInvoiceDetail> purchaseInvoiceDetails;
//
//    private LocalDate effectiveDate; // Ngày bắt đầu áp dụng giá này
//
//    private LocalDate expirationDate; // Ngày kết thúc áp dụng (tùy chọn)

    // Getter, setter, constructor

    public ProductSupplier() {
    }

    public ProductSupplier(Long id, Product product, Supplier supplier, Double purchasePrice) {
        this.id = id;
        this.product = product;
        this.supplier = supplier;
        this.purchasePrice = purchasePrice;
    }


}
