package com.vn.sbit.SpringMVC.service;

import com.vn.sbit.SpringMVC.dto.request.CategoryRequest;
import com.vn.sbit.SpringMVC.dto.request.ProductRequest;
import com.vn.sbit.SpringMVC.dto.response.ProductResponse;
import com.vn.sbit.SpringMVC.entity.Category;
import com.vn.sbit.SpringMVC.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAll();
    ProductResponse createProduct(ProductRequest request);
    ProductResponse findByProductName(String name);
    ProductResponse findById(Long id);
    ProductResponse updateProduct(Long id,ProductRequest request);
    void deleteProduct(Long id);
}
