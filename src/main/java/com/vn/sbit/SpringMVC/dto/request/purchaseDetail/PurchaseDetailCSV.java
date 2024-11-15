package com.vn.sbit.SpringMVC.dto.request.purchaseDetail;

import com.vn.sbit.SpringMVC.dto.response.PurchaseDetailResponse;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PurchaseDetailCSV {
    private Long id;
    private Long purchaseInvoiceId; // ID của purchaseInvoice
    private Long productSupplierId; // Tên của productSupplier
    private int quantity;
    private Double purchasePrice;
    private Double totalPrice;
    private Double debt;

    public PurchaseDetailCSV(PurchaseDetailResponse response) {
        this.id = response.getId();
        this.purchaseInvoiceId = response.getPurchaseInvoice().getId();
        this.productSupplierId = response.getProductSupplier().getId();
        this.quantity = response.getQuantity();
        this.purchasePrice = response.getPurchasePrice();
        this.totalPrice = response.getTotalPrice();
        this.debt = response.getDebt();
    }
}
