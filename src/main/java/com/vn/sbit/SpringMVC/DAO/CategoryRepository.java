package com.vn.sbit.SpringMVC.DAO;

import com.vn.sbit.SpringMVC.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category findByCategoryName(String name);
}
