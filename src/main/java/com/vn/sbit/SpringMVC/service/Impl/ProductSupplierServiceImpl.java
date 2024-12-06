package com.vn.sbit.SpringMVC.service.Impl;

import com.vn.sbit.SpringMVC.entity.ProductSupplier;
import com.vn.sbit.SpringMVC.repository.ProductSupplierRepository;
import com.vn.sbit.SpringMVC.service.ProductSupplierService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class ProductSupplierServiceImpl implements ProductSupplierService {
    ProductSupplierRepository productSupplierRepository;


    @Override
    public List<ProductSupplier> getAll() {
        return productSupplierRepository.findAll();
    }

}
