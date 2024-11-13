package com.vn.sbit.SpringMVC.service.Impl;

import com.vn.sbit.SpringMVC.dto.request.PurchaseDetailRequest;
import com.vn.sbit.SpringMVC.dto.response.PurchaseDetailResponse;
import com.vn.sbit.SpringMVC.entity.ProductSupplier;
import com.vn.sbit.SpringMVC.entity.PurchaseInvoice;
import com.vn.sbit.SpringMVC.entity.PurchaseInvoiceDetail;
import com.vn.sbit.SpringMVC.mapper.PurchaseDetailMapper;
import com.vn.sbit.SpringMVC.repository.ProductRepository;
import com.vn.sbit.SpringMVC.repository.ProductSupplierRepository;
import com.vn.sbit.SpringMVC.repository.PurchaseDetailRepository;
import com.vn.sbit.SpringMVC.repository.PurchaseRepository;
import com.vn.sbit.SpringMVC.service.PurchaseDetailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class PurchaseDetailServiceImpl implements PurchaseDetailService {
    PurchaseDetailRepository purchaseDetailRepository;
    ProductSupplierRepository productSupplierRepository;
    PurchaseDetailMapper purchaseDetailMapper;
    PurchaseRepository purchaseRepository;
    


    @Override
    public List<PurchaseDetailResponse> getAll() {
        return purchaseDetailRepository.findAll().stream().map(purchaseDetailMapper::toPurchaseDetailResponse).toList();
    }

    @Transactional
    @Override
    public PurchaseDetailResponse create(PurchaseDetailRequest request) {
        if(request == null ){
            throw new RuntimeException("Request null");
        }
        log.info("PurchaseInvoiceId{}",request.getPurchaseInvoiceId());

        PurchaseInvoice purchaseInvoice = purchaseRepository.findById(request.getPurchaseInvoiceId()).orElseThrow(
                () -> new RuntimeException("Purchase Invoice Id false")
        );
        ProductSupplier productSupplier = productSupplierRepository.findById(request.getProductSupplierId()).orElseThrow(
                () -> new RuntimeException("Product Supplier Id false")
        );
        productSupplier.setPurchasePrice(request.getPurchasePrice());

        PurchaseInvoiceDetail purchaseInvoiceDetail= purchaseDetailMapper.toPurchaseInvoiceDetail(request);
        purchaseInvoiceDetail.setProductSupplier(productSupplier);
        purchaseInvoiceDetail.setPurchaseInvoice(purchaseInvoice);
        purchaseInvoiceDetail.setTotalPrice(request.getPurchasePrice()*request.getQuantity());
        purchaseDetailRepository.save(purchaseInvoiceDetail);

        return purchaseDetailMapper.toPurchaseDetailResponse(purchaseInvoiceDetail);
    }

    @Override
    public PurchaseDetailResponse updateById(Long id, PurchaseInvoiceDetail purchaseInvoiceDetail) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<PurchaseDetailResponse> getPurchaseInvoiceDetailsByPurchaseInvoiceId(Long purchaseInvoiceId) {
        return purchaseDetailRepository.findPurchaseDetailsByPurchaseId(purchaseInvoiceId).stream().map(purchaseDetailMapper::toPurchaseDetailResponse).toList();
    }
}
