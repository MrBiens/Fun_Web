package com.vn.sbit.SpringMVC.repository;

import com.vn.sbit.SpringMVC.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category findByCategoryName(String name);

    @Query("SELECT c FROM Category c WHERE c.categoryName LIKE %?1%")
    List<Category> searchCategory(String categoryName);
}
