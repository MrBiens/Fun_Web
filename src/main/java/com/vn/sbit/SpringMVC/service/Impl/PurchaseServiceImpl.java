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
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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
    public PurchaseInvoice findById(Long id) {
        return purchaseRepository.findById(id).orElseThrow(() -> new RuntimeException("Purchase Id not found"));
    }

    @Transactional
    @Override
    public PurchaseResponse create(PurchaseCreateRequest request) {
        if(purchaseRepository.existsByPurchaseInvoiceName(request.getPurchaseInvoiceName()) ){
            throw new RuntimeException("Purchase Name exist");
        }
        Supplier supplier = supplierRepository.findById(request.getSupplierId()).orElseThrow(() -> new RuntimeException("Supplier Id not found"));

        PurchaseInvoice purchaseInvoice = purchaseMapper.toPurchaseInvoice(request);
        purchaseInvoice.setSupplier(supplier);
        purchaseInvoice.setImportDate(LocalDate.now());
        purchaseRepository.save(purchaseInvoice);

        return purchaseMapper.toPurchaseResponse(purchaseInvoice);
    }

    @Override
    @Transactional
    public PurchaseResponse updateById(Long id, PurchaseUpdateRequest request) {
        PurchaseInvoice purchaseInvoice = findById(id);

        Supplier supplier = supplierRepository.findById(request.getSupplierId()).orElseThrow(() -> new RuntimeException("Supplier id not found"));

        purchaseMapper.updatePurchase(purchaseInvoice,request);
        purchaseInvoice.setSupplier(supplier);
        purchaseInvoice.setImportDate(LocalDate.now());
        purchaseRepository.save(purchaseInvoice);

        return purchaseMapper.toPurchaseResponse(purchaseInvoice);
    }
    @Transactional
    @Override
    public void deleteById(Long id) {
        if (!purchaseRepository.existsById(id)) {
            throw new RuntimeException("Purchase id not found");
        }
        purchaseRepository.deleteById(id);
    }

}
