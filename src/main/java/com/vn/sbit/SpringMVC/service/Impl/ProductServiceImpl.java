package com.vn.sbit.SpringMVC.service.Impl;

import com.vn.sbit.SpringMVC.entity.ProductSupplier;
import com.vn.sbit.SpringMVC.entity.Supplier;
import com.vn.sbit.SpringMVC.repository.CategoryRepository;
import com.vn.sbit.SpringMVC.repository.ProductRepository;
import com.vn.sbit.SpringMVC.dto.request.ProductRequest;
import com.vn.sbit.SpringMVC.dto.response.ProductResponse;
import com.vn.sbit.SpringMVC.entity.Category;
import com.vn.sbit.SpringMVC.entity.Product;
import com.vn.sbit.SpringMVC.mapper.ProductMapper;
import com.vn.sbit.SpringMVC.repository.ProductSupplierRepository;
import com.vn.sbit.SpringMVC.service.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);
    ProductRepository productRepository;
    CategoryRepository categoryRepository;
    ProductMapper productMapper;
    ProductSupplierRepository productSupplierRepository;

    @Override
    public List<ProductResponse> getAll() {
//        return productRepository.findAll().stream().map(product -> productMapper.toProductResponse(product)).collect(Collectors.toList());
        return productRepository.findAll().stream().map(productMapper::toProductResponse).toList();

    }

    @Transactional
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

    @Transactional
    @Override
    public ProductResponse createProductAndSupplier(ProductRequest request, Supplier supplier,ProductSupplier productSupplier) {
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

        productSupplier.setProduct(product);
        productSupplier.setSupplier(supplier);

        productRepository.save(product);
        productSupplierRepository.save(productSupplier);

        return productMapper.toProductResponse(product);
    }

    @Override
    public Product findByProductName(String name) {
        return null;
    }


    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    public void updateProduct(Long id, ProductRequest request) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product Not found"));
        if (request.getCategoryId() == null) {
            throw new IllegalArgumentException("Category ID must not be null");
        }
        Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(() -> new RuntimeException("Category not found"));

        productMapper.updateProduct(product, request);
        product.setCategory(category);
        productRepository.save(product);


    }


    @Override
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Category Not found");
        }
        productRepository.deleteById(id);
    }


}
