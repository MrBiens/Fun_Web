package com.vn.sbit.SpringMVC.service.Impl;

import com.vn.sbit.SpringMVC.DAO.SupplierRepository;
import com.vn.sbit.SpringMVC.dto.request.SupplierRequest;
import com.vn.sbit.SpringMVC.dto.response.SupplierResponse;
import com.vn.sbit.SpringMVC.entity.Supplier;
import com.vn.sbit.SpringMVC.mapper.SupplierMapper;
import com.vn.sbit.SpringMVC.service.SupplierService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class SupplierServiceImpl implements SupplierService {

    SupplierRepository supplierRepository;
    SupplierMapper supplierMapper;

    @Override
    public List<SupplierResponse> getAll() {

        return supplierRepository.findAll().stream().map(supplier -> supplierMapper.toSupplierResponse(supplier)).toList();
    }

    @Override
    public SupplierResponse create(SupplierRequest request) {
        return null;
    }



    @Override
    public SupplierResponse updateById(Long id) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
