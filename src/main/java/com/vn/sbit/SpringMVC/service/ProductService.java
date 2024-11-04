package com.vn.sbit.SpringMVC.service;

import com.vn.sbit.SpringMVC.dto.request.CategoryRequest;
import com.vn.sbit.SpringMVC.dto.request.ProductRequest;
import com.vn.sbit.SpringMVC.dto.response.ProductResponse;
import com.vn.sbit.SpringMVC.entity.Category;
import com.vn.sbit.SpringMVC.entity.Product;

import java.util.List;

public interface ProductService {
    List<ProductResponse> getAll();
    ProductResponse createProduct(ProductRequest request);
    Product findByProductName(String name);
    Product findById(Long id);
    void updateProduct(Long id,ProductRequest request);
    void deleteProduct(Long id);
}
