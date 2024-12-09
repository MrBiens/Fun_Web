package com.vn.sbit.SpringMVC.service.Impl;

import com.vn.sbit.SpringMVC.dto.request.ProductSupplierRequest;
import com.vn.sbit.SpringMVC.dto.response.ProductSupplierResponse;
import com.vn.sbit.SpringMVC.entity.ProductSupplier;
import com.vn.sbit.SpringMVC.mapper.ProductSupplierMapper;
import com.vn.sbit.SpringMVC.repository.ProductSupplierRepository;
import com.vn.sbit.SpringMVC.service.ProductSupplierService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class ProductSupplierServiceImpl implements ProductSupplierService<ProductSupplierRequest,ProductSupplier,ProductSupplierResponse> {
    EntityManager entityManager;
    ProductSupplierRepository productSupplierRepository;
    ProductSupplierMapper productSupplierMapper;


    @Override
    public List<ProductSupplierResponse> getAll() {
        TypedQuery<ProductSupplier> query = entityManager.createQuery(
                "SELECT ps FROM ProductSupplier ps " +
                        "JOIN FETCH ps.product p " +
                        "JOIN FETCH ps.supplier s",
                ProductSupplier.class
        );
        List<ProductSupplier> productSuppliers = query.getResultList();
        return productSuppliers.stream()
                .map(productSupplierMapper::toProductSupplierResponse)
                .toList();
    }


    @Override
    public ProductSupplierResponse create(Object s) {
        return null;
    }

    @Override
    public ProductSupplierResponse updateById(Long id, Object object) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }


}
