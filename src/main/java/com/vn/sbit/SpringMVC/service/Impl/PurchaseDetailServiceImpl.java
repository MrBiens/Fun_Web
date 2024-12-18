package com.vn.sbit.SpringMVC.service.Impl;

import com.vn.sbit.SpringMVC.dto.request.purchaseDetail.PurchaseDetailRequest;
import com.vn.sbit.SpringMVC.dto.request.purchaseDetail.PurchaseDetailUpdateRequest;
import com.vn.sbit.SpringMVC.dto.response.PurchaseDetailResponse;
import com.vn.sbit.SpringMVC.entity.ProductSupplier;
import com.vn.sbit.SpringMVC.entity.PurchaseInvoice;
import com.vn.sbit.SpringMVC.entity.PurchaseInvoiceDetail;
import com.vn.sbit.SpringMVC.mapper.PurchaseDetailMapper;
import com.vn.sbit.SpringMVC.repository.ProductSupplierRepository;
import com.vn.sbit.SpringMVC.repository.PurchaseDetailRepository;
import com.vn.sbit.SpringMVC.repository.PurchaseRepository;
import com.vn.sbit.SpringMVC.service.PurchaseDetailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        return purchaseDetailRepository.findAll(Sort.by("id").ascending()).stream().map(purchaseDetailMapper::toPurchaseDetailResponse).toList();
    }

    @Transactional
    @Override
    public PurchaseDetailResponse create(PurchaseDetailRequest request) {
        if(request == null ){
            throw new RuntimeException("Request null");
        }
        PurchaseInvoice purchaseInvoice = purchaseRepository.findById(request.getPurchaseInvoiceId()).orElseThrow(
                () -> new RuntimeException("Purchase Invoice Id false")
        );
        ProductSupplier productSupplier = productSupplierRepository.findById(request.getProductSupplierId()).orElseThrow(
                () -> new RuntimeException("Product Supplier Id false")
        );

        PurchaseInvoiceDetail purchaseInvoiceDetail= purchaseDetailMapper.toPurchaseInvoiceDetail(request);
        purchaseInvoiceDetail.setProductSupplier(productSupplier);
        purchaseInvoiceDetail.setPurchaseInvoice(purchaseInvoice);
        purchaseInvoiceDetail.setTotalPrice(productSupplier.getPurchasePrice()*request.getQuantity());

        purchaseDetailRepository.save(purchaseInvoiceDetail);

        return purchaseDetailMapper.toPurchaseDetailResponse(purchaseInvoiceDetail);
    }

    @Transactional
    @Override
    public PurchaseDetailResponse updateById(Long id, PurchaseDetailUpdateRequest updateRequest) {
        PurchaseInvoiceDetail invoiceDetail = purchaseDetailRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Purchase Detail Id ")
        );
        ProductSupplier productSupplier = productSupplierRepository.findById(updateRequest.getProductSupplierId()).orElseThrow(
                () -> new RuntimeException("Product Supplier Id false")
        );

        productSupplier.setPurchasePrice(updateRequest.getPurchasePrice());

        purchaseDetailMapper.updatePurchaseDetail(invoiceDetail,updateRequest);
        invoiceDetail.setProductSupplier(productSupplier);
        invoiceDetail.setTotalPrice(updateRequest.getPurchasePrice()*updateRequest.getQuantity());
        purchaseDetailRepository.save(invoiceDetail);

        return purchaseDetailMapper.toPurchaseDetailResponse(invoiceDetail);
    }

    @Override
    public void deleteById(Long id) {
        if(!purchaseDetailRepository.existsById(id)){
            throw  new RuntimeException("Purchase Detail Id Not Found");
        }
        purchaseDetailRepository.deleteById(id);
    }

    @Override
    public List<PurchaseDetailResponse> getPurchaseInvoiceDetailsByPurchaseInvoiceId(Long purchaseInvoiceId) {
        return purchaseDetailRepository.findPurchaseDetailsByPurchaseId(purchaseInvoiceId).stream().map(purchaseDetailMapper::toPurchaseDetailResponse).toList();
    }

    @Override
    public Double calculateTotalAmountByInvoiceId(Long purchaseInvoiceId) {
        List<PurchaseInvoiceDetail> details = purchaseDetailRepository.findPurchaseDetailsByPurchaseId(purchaseInvoiceId);
        // Tính tổng số tiền từ các chi tiết hóa đơn
        return details.stream()
                .mapToDouble(PurchaseInvoiceDetail::getTotalPrice)
                .sum();
    }

    @Override
    public PurchaseInvoiceDetail getById(Long purchaseDetailId) {
        return purchaseDetailRepository.findById(purchaseDetailId).orElseThrow(() ->  new RuntimeException("Purchase Detail Id False - PurchaseDetail null"));
    }


    @Override
    public Integer calculateTotalQuantityByInvoiceId(Long purchaseInvoiceId) {
        List<PurchaseInvoiceDetail> details = purchaseDetailRepository.findPurchaseDetailsByPurchaseId(purchaseInvoiceId);
        return Math.toIntExact(details.stream().mapToLong(PurchaseInvoiceDetail::getQuantity).sum());

    }

}
