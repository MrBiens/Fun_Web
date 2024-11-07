package com.vn.sbit.SpringMVC.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "purchase_invoice")
public class PurchaseInvoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "purchase_invoice_name")
    String purchaseInvoiceName;

    @OneToMany(mappedBy = "purchaseInvoice",cascade = {
            CascadeType.ALL
    })
    List<PurchaseInvoiceDetail> purchaseInvoiceDetail;


    // Tổng tiền của hóa đơn
    @Column(name = "total_amount")
    private Double totalAmount;

    // Các thuộc tính khác như supplier, date, etc.

    // Tính tổng tiền
    public void calculateTotalAmount() {
        this.totalAmount = purchaseInvoiceDetail.stream()
                .mapToDouble(PurchaseInvoiceDetail::getTotalPrice)
                .sum();
    }



}
