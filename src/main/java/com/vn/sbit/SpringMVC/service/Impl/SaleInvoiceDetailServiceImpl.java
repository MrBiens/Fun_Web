package com.vn.sbit.SpringMVC.service.Impl;

import com.vn.sbit.SpringMVC.dto.request.SaleInvoiceDetailRequest;
import com.vn.sbit.SpringMVC.dto.response.SaleInvoiceDetailResponse;
import com.vn.sbit.SpringMVC.entity.SaleInvoiceDetail;
import com.vn.sbit.SpringMVC.mapper.SaleInvoiceDetailMapper;
import com.vn.sbit.SpringMVC.service.SaleInvoiceDetailService;
import com.vn.sbit.SpringMVC.service.business.SaleInvoiceDetailBusinessService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/*
 * List<SaleInvoiceDetailResponse> findBySaleInvoiceId: trả về 1 list các chi tiết hóa đơn bằng mã hóa đơn
 * */
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class SaleInvoiceDetailServiceImpl implements SaleInvoiceDetailService {
    SaleInvoiceDetailBusinessService saleInvoiceDetailBusinessService;
    SaleInvoiceDetailMapper saleInvoiceDetailMapper;

    // no
    @Override
    public List<SaleInvoiceDetailResponse> getAll() {
        return null;
    }

    @Override
    public List<SaleInvoiceDetailResponse> findBySaleInvoiceId(Long saleInvoiceId) {
        return saleInvoiceDetailBusinessService.findListSaleInvoiceDetailById(saleInvoiceId)
                .stream().map(saleInvoiceDetailMapper::toSaleInvoiceDetailResponse).toList();
    }

    @Override
    public SaleInvoiceDetailResponse findSaleInvoiceDetailById(Long id) {
        return saleInvoiceDetailMapper.toSaleInvoiceDetailResponse(saleInvoiceDetailBusinessService.findSaleInvoiceDetailById(id));
    }

    @Override
    public int calculateTotalAmountByInvoiceId(Long saleInvoiceId) {
        return saleInvoiceDetailBusinessService.calculateTotalAmountByInvoiceId(saleInvoiceId);
    }

    @Override
    public int calculateTotalQuantityByInvoiceId(Long saleInvoiceId) {
        return saleInvoiceDetailBusinessService.calculateTotalQuantityByInvoiceId(saleInvoiceId);
    }

    @Override
    public SaleInvoiceDetailResponse create(SaleInvoiceDetailRequest request) {
        return saleInvoiceDetailMapper.toSaleInvoiceDetailResponse(saleInvoiceDetailBusinessService.createSaleInvoiceDetail(request));
    }



    @Override
    public void updateSaleInvoiceDetail(Long id, SaleInvoiceDetailRequest request) {
        saleInvoiceDetailBusinessService.updateSaleInvoiceDetail(id,request);
    }


    @Override
    public void deleteById(Long id) {
        saleInvoiceDetailBusinessService.deleteSaleInvoiceDetail(id);
    }


    //no usage
    @Override
    public SaleInvoiceDetailResponse updateById(Long id, SaleInvoiceDetail saleInvoiceDetail) {
        return null;
    }


}
