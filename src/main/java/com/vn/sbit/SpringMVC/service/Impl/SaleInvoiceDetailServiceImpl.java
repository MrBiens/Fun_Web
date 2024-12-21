package com.vn.sbit.SpringMVC.service.Impl;

import com.vn.sbit.SpringMVC.dto.request.SaleInvoiceDetailRequest;
import com.vn.sbit.SpringMVC.dto.response.SaleInvoiceDetailResponse;
import com.vn.sbit.SpringMVC.entity.SaleInvoiceDetail;
import com.vn.sbit.SpringMVC.mapper.SaleInvoiceDetailMapper;
import com.vn.sbit.SpringMVC.repository.ProductRepository;
import com.vn.sbit.SpringMVC.repository.SaleInvoiceDetailRepository;
import com.vn.sbit.SpringMVC.repository.SaleInvoiceRepository;
import com.vn.sbit.SpringMVC.service.SaleInvoiceDetailService;
import com.vn.sbit.SpringMVC.service.business.SaleInvoiceDetailBusinessService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class SaleInvoiceDetailServiceImpl implements SaleInvoiceDetailService {
    SaleInvoiceDetailBusinessService saleInvoiceDetailBusinessService;
    SaleInvoiceDetailRepository saleInvoiceDetailRepository;
    SaleInvoiceDetailMapper saleInvoiceDetailMapper;
    SaleInvoiceRepository saleInvoiceRepository;
    ProductRepository productRepository;

    @Override
    public List<SaleInvoiceDetailResponse> getAll() {
        return saleInvoiceDetailRepository.findAll().stream().map(saleInvoiceDetailMapper::toSaleInvoiceDetailResponse).toList();
    }

    @Override
    public List<SaleInvoiceDetailResponse> findBySaleInvoiceId(Long saleInvoiceId) {
        return saleInvoiceDetailRepository.findSaleInvoiceDetailBySaleInvoiceId(saleInvoiceId).stream().map(saleInvoiceDetailMapper::toSaleInvoiceDetailResponse).toList();
    }


    @Override
    public SaleInvoiceDetailResponse findSaleInvoiceDetailById(Long id) {
        return saleInvoiceDetailMapper.toSaleInvoiceDetailResponse(saleInvoiceDetailBusinessService.findSaleInvoiceDetailById(id));
    }

    @Transactional
    @Override
    public SaleInvoiceDetailResponse create(SaleInvoiceDetailRequest s) {
        return null;
    }



    @Transactional
    @Override
    public void updateSaleInvoiceDetail(Long id, SaleInvoiceDetailRequest request) {

    }


    @Transactional
    @Override
    public void deleteById(Long id) {

    }



    @Override
    public SaleInvoiceDetailResponse updateById(Long id, SaleInvoiceDetail saleInvoiceDetail) {
        return null;
    }


}
