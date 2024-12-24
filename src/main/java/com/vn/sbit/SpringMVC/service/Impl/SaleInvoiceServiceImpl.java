package com.vn.sbit.SpringMVC.service.Impl;

import com.vn.sbit.SpringMVC.dto.request.SaleInvoiceRequest;
import com.vn.sbit.SpringMVC.dto.response.SaleInvoiceResponse;
import com.vn.sbit.SpringMVC.entity.Customer;
import com.vn.sbit.SpringMVC.entity.Employee;
import com.vn.sbit.SpringMVC.entity.SaleInvoice;
import com.vn.sbit.SpringMVC.exception.AppException;
import com.vn.sbit.SpringMVC.exception.ErrorCode;
import com.vn.sbit.SpringMVC.mapper.SaleInvoiceMapper;
import com.vn.sbit.SpringMVC.repository.CustomerRepository;
import com.vn.sbit.SpringMVC.repository.EmployeeRepository;
import com.vn.sbit.SpringMVC.repository.SaleInvoiceRepository;
import com.vn.sbit.SpringMVC.service.SaleInvoiceService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Service
@RequiredArgsConstructor
public class SaleInvoiceServiceImpl implements SaleInvoiceService {
    SaleInvoiceRepository saleInvoiceRepository;
    SaleInvoiceMapper saleInvoiceMapper;
    EmployeeRepository employeeRepository;
    CustomerRepository customerRepository;


    @Override
    public void updateSaleInvoice(Long id, SaleInvoiceRequest request) {
        SaleInvoice saleInvoice=saleInvoiceRepository.findById(id).orElseThrow(()-> new RuntimeException("Cannot find SaleInvoice By Id"));
        saleInvoiceMapper.updateSaleInvoice(saleInvoice,request);

        Employee employee = employeeRepository.findById(request.getEmployeeId()).orElseThrow(()-> new RuntimeException("Employee Not Found"));
        if(saleInvoice.getEmployee() != employee){
            saleInvoice.setEmployee(employee);
        }
        Customer customer = customerRepository.findById(request.getCustomerId()).orElseThrow(()-> new RuntimeException("Customer Not Found"));
        if(saleInvoice.getCustomer() != customer){
            saleInvoice.setCustomer(customer);
        }
        saleInvoiceRepository.save(saleInvoice);
    }

    @Override
    public SaleInvoiceResponse findById(Long id) {
        return saleInvoiceRepository.findById(id)
                .map(saleInvoiceMapper::toSaleInvoiceResponse).orElseThrow(() -> new AppException(ErrorCode.SALE_INVOICE_NOT_FOUND));
    }

    @Override
    public SaleInvoice findSaleInvoiceById(Long id) {
        return saleInvoiceRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.SALE_INVOICE_NOT_FOUND));
    }


    @Override
    public List<SaleInvoiceResponse> getAll() {
        return saleInvoiceRepository.findAll().stream().map(saleInvoiceMapper::toSaleInvoiceResponse).toList();
    }


    @Transactional
    @Override
    public SaleInvoiceResponse create(SaleInvoiceRequest request) {
        Employee employee = employeeRepository.findById(request.getEmployeeId()).orElseThrow(()-> new RuntimeException("Employee Not Found"));
        Customer customer = customerRepository.findById(request.getCustomerId()).orElseThrow(()-> new RuntimeException("Customer Not Found"));

        SaleInvoice saleInvoice = saleInvoiceMapper.toSaleInvoice(request);
        saleInvoice.setCustomer(customer);
        saleInvoice.setEmployee(employee);
        saleInvoice.setDayCreate(LocalDate.now());
        saleInvoice.setTotalAmount(0);

        saleInvoiceRepository.save(saleInvoice);

        return saleInvoiceMapper.toSaleInvoiceResponse(saleInvoice);
    }


    @Override
    public SaleInvoiceResponse updateById(Long id, SaleInvoice saleInvoice) {
        return null;
    }

    @Override
    public void deleteById(Long id) {
        if(saleInvoiceRepository.existsById(id)){
            saleInvoiceRepository.deleteById(id);
        }
    }

}
