package com.vn.sbit.SpringMVC.service.business;

import com.vn.sbit.SpringMVC.entity.SaleInvoiceDetail;
import com.vn.sbit.SpringMVC.exception.ErrorCode;
import com.vn.sbit.SpringMVC.exception.AppException;
import com.vn.sbit.SpringMVC.repository.SaleInvoiceDetailRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class SaleInvoiceDetailBusinessService {
    SaleInvoiceDetailRepository saleInvoiceDetailRepository;

    // Logic nghiệp vụ để tìm kiếm chi tiết hóa đơn
    public SaleInvoiceDetail findSaleInvoiceDetailById(Long id) {
        return saleInvoiceDetailRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.SALE_INVOICE_DETAIL_NOT_FOUND));
    }


}
