package com.vn.sbit.SpringMVC.service.business;

import com.vn.sbit.SpringMVC.dto.request.SaleInvoiceDetailRequest;
import com.vn.sbit.SpringMVC.dto.response.SaleInvoiceDetailResponse;
import com.vn.sbit.SpringMVC.entity.Product;
import com.vn.sbit.SpringMVC.entity.PurchaseInvoiceDetail;
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

    public int calculateTotalAmountByInvoiceId(Long saleInvoiceId){
        List<SaleInvoiceDetail> details=saleInvoiceDetailRepository.findSaleInvoiceDetailBySaleInvoiceId(saleInvoiceId);
        return details.stream().mapToInt(SaleInvoiceDetail::getTotalPrice).sum();
    }

    public int calculateTotalQuantityByInvoiceId(Long saleInvoiceId){
        List<SaleInvoiceDetail> details=saleInvoiceDetailRepository.findSaleInvoiceDetailBySaleInvoiceId(saleInvoiceId);
        return details.stream().mapToInt(SaleInvoiceDetail::getQuantity).sum();
    }

    private void validateProductQuantity(int requestedQuantity, Product product) {
        if (product.getQuantity() <= 0 || product.getQuantity() < requestedQuantity) {
            throw new AppException(ErrorCode.PRODUCT_NOT_QUANTITY);
        }
    }


    @Transactional
    public SaleInvoiceDetail createSaleInvoiceDetail(SaleInvoiceDetailRequest request){
        Product product = productService.findById(request.getProductId());

        validateProductQuantity(request.getQuantity(), product);


        SaleInvoice saleInvoice = saleInvoiceService.findSaleInvoiceById(request.getSaleInvoiceId());


        SaleInvoiceDetail saleInvoiceDetail = saleInvoiceDetailMapper.toSaleInvoiceDetail(request);

        saleInvoiceDetail.setProduct(product);
        saleInvoiceDetail.setSaleInvoice(saleInvoice);
        saleInvoiceDetail.setTotalPrice(request.getQuantity()* request.getPrice());

        saleInvoiceDetailRepository.save(saleInvoiceDetail);

        productService.updateQuantity(saleInvoiceDetail.getProduct().getId(),product.getQuantity() - request.getQuantity());

        saleInvoiceService.updateTotalAmount(saleInvoiceDetail.getSaleInvoice().getId(), calculateTotalAmountByInvoiceId(saleInvoiceDetail.getSaleInvoice().getId()));

        return saleInvoiceDetail;
    }

    @Transactional
    public void updateSaleInvoiceDetail(Long id,SaleInvoiceDetailRequest request){
        SaleInvoiceDetail saleInvoiceDetail = findSaleInvoiceDetailById(id);
        int currentSaleDetailQuantity=saleInvoiceDetail.getQuantity();

        Long productId = saleInvoiceDetail.getProduct().getId();

        saleInvoiceDetailMapper.updateSaleInvoiceDetail(saleInvoiceDetail,request);

        int currentProductQuantity=0;

        if(!Objects.equals(request.getProductId(), saleInvoiceDetail.getProduct().getId())){
            Product oldProduct = productService.findById(productId);
            productService.updateQuantity(oldProduct.getId(), oldProduct.getQuantity() + currentSaleDetailQuantity);


            Product newProduct = productService.findById(request.getProductId());
            validateProductQuantity(request.getQuantity(), newProduct);
            saleInvoiceDetail.setProduct(newProduct);
            currentProductQuantity=newProduct.getQuantity()-request.getQuantity();

        }else{
            if(currentSaleDetailQuantity != request.getQuantity()){
                Product product = productService.findById(productId);
                currentProductQuantity = product.getQuantity()+currentSaleDetailQuantity-request.getQuantity();
                productService.updateQuantity(product.getId(),currentProductQuantity);

            }
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

        productService.updateQuantity(saleInvoiceDetail.getProduct().getId(),currentProductQuantity);

        saleInvoiceService.updateTotalAmount(saleInvoiceDetail.getSaleInvoice().getId(), calculateTotalAmountByInvoiceId(saleInvoiceDetail.getSaleInvoice().getId()));

    }

    @Transactional
    public void deleteSaleInvoiceDetail(Long id){
        SaleInvoiceDetail saleInvoiceDetail = findSaleInvoiceDetailById(id);

        Long productId = saleInvoiceDetail.getProduct().getId();
        Product product = productService.findById(productId);
        int newTotalQuantity=saleInvoiceDetail.getQuantity();


        SaleInvoice saleInvoice = saleInvoiceDetail.getSaleInvoice();

        // Xóa SaleInvoiceDetail
        saleInvoiceDetailRepository.delete(saleInvoiceDetail);

        // Tính lại totalAmount cho SaleInvoice
        int newTotalAmount = calculateTotalAmountByInvoiceId(saleInvoice.getId());

        productService.updateQuantity(product.getId(),product.getQuantity()+newTotalQuantity);

        // Cập nhật totalAmount cho SaleInvoice
        saleInvoiceService.updateTotalAmount(saleInvoice.getId(), newTotalAmount);
    }




}
