package com.vn.sbit.SpringMVC.service.Impl;

import com.vn.sbit.SpringMVC.dto.request.ProductSupplierRequest;
import com.vn.sbit.SpringMVC.dto.response.ProductSupplierResponse;
import com.vn.sbit.SpringMVC.entity.Product;
import com.vn.sbit.SpringMVC.entity.ProductSupplier;
import com.vn.sbit.SpringMVC.entity.Supplier;
import com.vn.sbit.SpringMVC.mapper.ProductSupplierMapper;
import com.vn.sbit.SpringMVC.repository.ProductSupplierRepository;
import com.vn.sbit.SpringMVC.service.ProductService;
import com.vn.sbit.SpringMVC.service.ProductSupplierService;
import com.vn.sbit.SpringMVC.service.SupplierService;
import jakarta.persistence.EntityManager;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class ProductSupplierServiceImpl implements ProductSupplierService {
    private static final Logger log = LoggerFactory.getLogger(ProductSupplierServiceImpl.class);
    EntityManager entityManager;
    ProductSupplierRepository productSupplierRepository;
    ProductSupplierMapper productSupplierMapper;
    ProductService productService;
    SupplierService supplierService;


    @Transactional
    @Override
    public List<ProductSupplierResponse> getAll() {
//        TypedQuery<ProductSupplier> query = entityManager.createQuery(
//                "SELECT ps FROM ProductSupplier ps " +
//                        "JOIN FETCH ps.product p " +
//                        "JOIN FETCH ps.supplier s",
//                ProductSupplier.class
//        );
//        List<ProductSupplier> productSuppliers = query.getResultList();
//        return productSuppliers.stream()
//                .map(productSupplierMapper::toProductSupplierResponse)
//                .toList();
        return productSupplierRepository.findAll().stream().map(productSupplierMapper::toProductSupplierResponse).toList();
    }

    @Transactional
    @Override
    public ProductSupplierResponse create(ProductSupplierRequest request) {
        Product product = Optional.ofNullable(productService.findById(request.getProductId()))
                .orElseThrow(() -> new RuntimeException("Cannot find Product"));
        Supplier supplier = Optional.ofNullable(supplierService.findById(request.getSupplierId()))
                .orElseThrow(() -> new RuntimeException("Cannot find Supplier"));

        ProductSupplier productSupplier = productSupplierMapper.toProductSupplier(request);
        productSupplier.setProduct(product);
        productSupplier.setSupplier(supplier);
        productSupplierRepository.save(productSupplier);
        log.info("ProductSupplier Impl:{}",productSupplier);
        System.out.println(productSupplier);
        return productSupplierMapper.toProductSupplierResponse(productSupplier);
    }

    @Override
    public ProductSupplierResponse updateById(Long id, ProductSupplier productSupplier) {
        return null;
    }


    @Override
    public void deleteById(Long id) {

    }


}
