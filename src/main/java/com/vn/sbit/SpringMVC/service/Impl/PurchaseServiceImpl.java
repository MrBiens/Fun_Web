package com.vn.sbit.SpringMVC.service.Impl;

import com.vn.sbit.SpringMVC.dto.request.purchase.PurchaseCreateRequest;
import com.vn.sbit.SpringMVC.dto.request.purchase.PurchaseUpdateRequest;
import com.vn.sbit.SpringMVC.dto.response.PurchaseResponse;
import com.vn.sbit.SpringMVC.entity.PurchaseInvoice;
import com.vn.sbit.SpringMVC.entity.Supplier;
import com.vn.sbit.SpringMVC.mapper.PurchaseMapper;
import com.vn.sbit.SpringMVC.repository.PurchaseRepository;
import com.vn.sbit.SpringMVC.repository.SupplierRepository;
import com.vn.sbit.SpringMVC.service.PurchaseService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class PurchaseServiceImpl implements PurchaseService {
    PurchaseRepository purchaseRepository;
    SupplierRepository supplierRepository;
    PurchaseMapper purchaseMapper;

    @Override
    public List<PurchaseResponse> getAll() {
        return purchaseRepository.findAll().stream().map(purchaseMapper::toPurchaseResponse).toList();
    }

    @Override
    public PurchaseResponse create(PurchaseCreateRequest request) {
        if(purchaseRepository.existsByPurchaseInvoiceName(request.getPurchaseInvoiceName()) ){
            throw new RuntimeException("Purchase Name exist");
        }
        Supplier supplier = supplierRepository.findById(request.getSupplierId()).orElseThrow(() -> new RuntimeException("Supplier Id not found"));

        PurchaseInvoice purchaseInvoice = purchaseMapper.toPurchaseInvoice(request);
        purchaseInvoice.setSupplier(supplier);
        purchaseRepository.save(purchaseInvoice);

        return purchaseMapper.toPurchaseResponse(purchaseInvoice);
    }

    @Override
    public PurchaseResponse updateById(Long id, PurchaseUpdateRequest purchaseUpdateRequest) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
