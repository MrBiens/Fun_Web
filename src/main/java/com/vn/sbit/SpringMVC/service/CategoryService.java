package com.vn.sbit.SpringMVC.service;

import com.vn.sbit.SpringMVC.dto.request.CategoryRequest;
import com.vn.sbit.SpringMVC.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAll();
    Category createCategory(CategoryRequest request);
    Category findByCategoryName(String name);
    Category findById(Long id);
    Category updateCategory(Long id,CategoryRequest request);
    void deleteCategory(Long id);



}
