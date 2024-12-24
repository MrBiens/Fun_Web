package com.vn.sbit.SpringMVC.service.business;

import com.vn.sbit.SpringMVC.dto.request.SaleInvoiceDetailRequest;
import com.vn.sbit.SpringMVC.dto.response.SaleInvoiceDetailResponse;
import com.vn.sbit.SpringMVC.entity.Product;
import com.vn.sbit.SpringMVC.entity.SaleInvoice;
import com.vn.sbit.SpringMVC.entity.SaleInvoiceDetail;
import com.vn.sbit.SpringMVC.exception.ErrorCode;
import com.vn.sbit.SpringMVC.exception.AppException;
import com.vn.sbit.SpringMVC.mapper.SaleInvoiceDetailMapper;
import com.vn.sbit.SpringMVC.repository.SaleInvoiceDetailRepository;
import com.vn.sbit.SpringMVC.service.ProductService;
import com.vn.sbit.SpringMVC.service.SaleInvoiceService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class SaleInvoiceDetailBusinessService {
    SaleInvoiceDetailRepository saleInvoiceDetailRepository;
    SaleInvoiceDetailMapper saleInvoiceDetailMapper;
    SaleInvoiceService saleInvoiceService;
    ProductService productService;

    // Logic nghiệp vụ để tìm kiếm chi tiết hóa đơn bằng mã hóa đơn
    public List<SaleInvoiceDetail> findListSaleInvoiceDetailById(Long saleInvoiceId) {
        return saleInvoiceDetailRepository.findSaleInvoiceDetailBySaleInvoiceId(saleInvoiceId).stream().toList();
    }

    // Logic nghiệp vụ để tìm kiếm chi tiết hóa đơn
    public SaleInvoiceDetail findSaleInvoiceDetailById(Long id) {
        return saleInvoiceDetailRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.SALE_INVOICE_DETAIL_NOT_FOUND));
    }

    @Transactional
    public SaleInvoiceDetail createSaleInvoiceDetail(SaleInvoiceDetailRequest request){
        Product product = productService.findById(request.getProductId());
        SaleInvoice saleInvoice = saleInvoiceService.findSaleInvoiceById(request.getSaleInvoiceId());

        SaleInvoiceDetail saleInvoiceDetail = saleInvoiceDetailMapper.toSaleInvoiceDetail(request);

        saleInvoiceDetail.setProduct(product);
        saleInvoiceDetail.setSaleInvoice(saleInvoice);
        saleInvoiceDetail.setTotalPrice(request.getQuantity()* request.getPrice());

        return saleInvoiceDetailRepository.save(saleInvoiceDetail);
    }

    @Transactional
    public void updateSaleInvoiceDetail(Long id,SaleInvoiceDetailRequest request){
        SaleInvoiceDetail saleInvoiceDetail = findSaleInvoiceDetailById(id);

        saleInvoiceDetailMapper.updateSaleInvoiceDetail(saleInvoiceDetail,request);

        if(!Objects.equals(request.getProductId(), saleInvoiceDetail.getProduct().getId())){
            Product product = productService.findById(request.getProductId());
            saleInvoiceDetail.setProduct(product);

        }
        if(!Objects.equals(request.getSaleInvoiceId(),saleInvoiceDetail.getSaleInvoice().getId())){
            SaleInvoice saleInvoice = saleInvoiceService.findSaleInvoiceById(request.getSaleInvoiceId());
            saleInvoiceDetail.setSaleInvoice(saleInvoice);
        }

        int totalPriceRequest = request.getPrice() * request.getQuantity();
        if(saleInvoiceDetail.getTotalPrice() != totalPriceRequest){
            saleInvoiceDetail.setTotalPrice(totalPriceRequest);
        }

        saleInvoiceDetailRepository.save(saleInvoiceDetail);
    }

    @Transactional
    public void deleteSaleInvoiceDetail(Long id){
        if(saleInvoiceDetailRepository.existsById(id)){
            saleInvoiceDetailRepository.deleteById(id);
        }
    }




}
