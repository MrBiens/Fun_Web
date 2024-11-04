package com.vn.sbit.SpringMVC.service.Impl;

import com.vn.sbit.SpringMVC.DAO.CategoryRepository;
import com.vn.sbit.SpringMVC.DAO.ProductRepository;
import com.vn.sbit.SpringMVC.dto.request.ProductRequest;
import com.vn.sbit.SpringMVC.dto.response.ProductResponse;
import com.vn.sbit.SpringMVC.entity.Category;
import com.vn.sbit.SpringMVC.entity.Product;
import com.vn.sbit.SpringMVC.mapper.ProductMapper;
import com.vn.sbit.SpringMVC.service.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
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
    public List<ProductResponse> getAll() {
        return productRepository.findAll().stream().map(product -> productMapper.toProductResponse(product)).toList();
    }

    @Override
    public ProductResponse createProduct(ProductRequest request) {
        if (!categoryRepository.existsById(request.getCategoryId())) {
            throw new RuntimeException("Category Not found");
        }
        if (productRepository.existsByProductName(request.getProductName())) {
            throw new RuntimeException("Product exists");
        }
        Category category;
        category = categoryRepository.findById(request.getCategoryId()).orElseThrow();

        Product product = productMapper.toProduct(request);
        product.setCategory(category);
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
