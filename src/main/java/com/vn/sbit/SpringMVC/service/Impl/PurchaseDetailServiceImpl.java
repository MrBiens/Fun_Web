package com.vn.sbit.SpringMVC.service.Impl;

import com.vn.sbit.SpringMVC.dto.request.PurchaseDetailRequest;
import com.vn.sbit.SpringMVC.dto.response.PurchaseDetailResponse;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class PurchaseDetailServiceImpl implements PurchaseDetailService {
    PurchaseDetailRepository purchaseDetailRepository;
    ProductSupplierRepository productSupplierRepository;
    PurchaseDetailMapper purchaseDetailMapper;
    


    @Override
    public List<PurchaseDetailResponse> getAll() {
        return purchaseDetailRepository.findAll().stream().map(purchaseDetailMapper::toPurchaseDetailResponse).toList();
    }

    @Transactional
    @Override
    public PurchaseDetailResponse create(PurchaseDetailRequest request) {
        return null;
    }

    @Override
    public PurchaseDetailResponse updateById(Long id, PurchaseInvoiceDetail purchaseInvoiceDetail) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
