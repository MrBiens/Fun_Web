package com.vn.sbit.SpringMVC.service;

import com.vn.sbit.SpringMVC.dto.request.CategoryRequest;
import com.vn.sbit.SpringMVC.dto.request.ProductRequest;
import com.vn.sbit.SpringMVC.dto.response.ProductResponse;
import com.vn.sbit.SpringMVC.entity.Category;
import com.vn.sbit.SpringMVC.entity.Product;
import com.vn.sbit.SpringMVC.entity.ProductSupplier;
import com.vn.sbit.SpringMVC.entity.Supplier;

import java.util.List;

public interface ProductService {
    List<ProductResponse> getAll();
    ProductResponse createProduct(ProductRequest request);
    ProductResponse createProductAndSupplier(ProductRequest request, Supplier supplier, ProductSupplier productSupplier);

    List<Product> findAllByProductNameIgnoreCase(String name);
    Product findById(Long id);
    void updateProduct(Long id,ProductRequest request);
    void deleteProduct(Long id);
}
