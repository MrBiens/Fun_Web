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
    @Column(name = "id",unique = true)
    Long id;

    @Column(name = "purchase_invoice_name",unique = true,columnDefinition = "VARCHAR(255) COLLATE utf8mb4_unicode_ci",length = 20)
    String purchaseInvoiceName;

    @ManyToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "supplier_id")
    Supplier supplier;

    @OneToMany(mappedBy = "purchaseInvoice",cascade = {
            CascadeType.ALL
    })
    List<PurchaseInvoiceDetail> purchaseInvoiceDetail;







}
