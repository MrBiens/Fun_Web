package com.vn.sbit.SpringMVC.service.Impl;

import com.vn.sbit.SpringMVC.DAO.CategoryRepository;
import com.vn.sbit.SpringMVC.DAO.ProductRepository;
import com.vn.sbit.SpringMVC.dto.request.ProductRequest;
import com.vn.sbit.SpringMVC.dto.response.ProductResponse;
import com.vn.sbit.SpringMVC.entity.Product;
import com.vn.sbit.SpringMVC.mapper.ProductMapper;
import com.vn.sbit.SpringMVC.service.ProductService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
     ProductRepository productRepository;
     CategoryRepository categoryRepository;
     ProductMapper productMapper;

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }


    @Override
    public ProductResponse createProduct(ProductRequest request) {
        if(categoryRepository.existsById(request.getCategoryId())){
            throw new RuntimeException("Category Not found");
        }
        Product product = productMapper.toProduct(request);
        productRepository.save(product);
        return productMapper.toProductResponse(product);

    }

    @Override
    public ProductResponse findByProductName(String name) {
        return null;
    }


    @Override
    public ProductResponse findById(Long id) {
        return null;
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductRequest request) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {

    }




}
