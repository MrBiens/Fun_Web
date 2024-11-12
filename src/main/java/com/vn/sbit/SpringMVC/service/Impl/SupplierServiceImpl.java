package com.vn.sbit.SpringMVC.service.Impl;

import com.vn.sbit.SpringMVC.dto.request.supp.SupplierUpdateRequest;
import com.vn.sbit.SpringMVC.repository.SupplierRepository;
import com.vn.sbit.SpringMVC.dto.request.supp.SupplierCreateRequest;
import com.vn.sbit.SpringMVC.dto.response.SupplierResponse;
import com.vn.sbit.SpringMVC.entity.Supplier;
import com.vn.sbit.SpringMVC.mapper.SupplierMapper;
import com.vn.sbit.SpringMVC.service.SupplierService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class SupplierServiceImpl implements SupplierService {

    SupplierRepository supplierRepository;
    SupplierMapper supplierMapper;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository, SupplierMapper supplierMapper) {
        this.supplierRepository = supplierRepository;
        this.supplierMapper = supplierMapper;
    }

    @Override
    public List<SupplierResponse> getAll() {
        return supplierRepository.findAll().stream().map(supplierMapper::toSupplierResponse).toList();
    }

    @Transactional
    @Override
    public SupplierResponse create(SupplierCreateRequest request) {
        if(supplierRepository.existsBySupplierName(request.getSupplierName()) ){
            throw new RuntimeException("Supplier exist");
        }
        Supplier supplier = supplierMapper.toSupplier(request);
        supplierRepository.save(supplier);
        return supplierMapper.toSupplierResponse(supplier);
    }

    @Override
    public Supplier findById(Long id) {
        return supplierRepository.findById(id).orElseThrow( () -> new RuntimeException("Supplier not found"));
    }



    @Override
    public SupplierResponse updateById(Long id, SupplierUpdateRequest request) {
        Supplier supplier =findById(id);
        if(supplierRepository.existsBySupplierName(request.getSupplierName()) ){
            throw new RuntimeException("Supplier Name exist");
        }
        supplierMapper.updateSupplier(supplier,request);
        supplierRepository.save(supplier);
        return supplierMapper.toSupplierResponse(supplier);
    }

    @Override
    public void deleteById(Long id) {
        if (!supplierRepository.existsById(id)) {
            throw new RuntimeException("Category Not found");
        }
        supplierRepository.deleteById(id);
    }

}
