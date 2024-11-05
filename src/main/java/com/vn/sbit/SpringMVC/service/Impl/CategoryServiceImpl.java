package com.vn.sbit.SpringMVC.service.Impl;

import com.vn.sbit.SpringMVC.DAO.CategoryRepository;
import com.vn.sbit.SpringMVC.dto.request.CategoryRequest;
import com.vn.sbit.SpringMVC.entity.Category;
import com.vn.sbit.SpringMVC.mapper.CategoryMapper;
import com.vn.sbit.SpringMVC.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper ;


    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository,CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper=categoryMapper;
    }


    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category createCategory(CategoryRequest request) {
        Category category= categoryMapper.toCategory(request);
        return categoryRepository.save(category);
    }

    @Override
    public Category findByCategoryName(String name) {
        return categoryRepository.findByCategoryName(name);
    }
    //  Optional bat loi null
    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow();
    }

    //find end create
    @Override
    public Category updateCategory(Long id,CategoryRequest request) {
        Category category= categoryRepository.findById(id).orElseThrow( () -> new RuntimeException("Category Not found"));
        categoryMapper.updateCategory(category,request);
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new RuntimeException("Category Not found");
        }
        categoryRepository.deleteById(id);
    }

    @Override
    public List<Category> searchByCategoryName(String name) {
        return categoryRepository.searchCategory(name);
    }

    @Override
    public Page<Category> pagination(Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo-1,2); //page number and data in page  - page 0 co 5 ban ghi
        return categoryRepository.findAll(pageable);
    }
}
