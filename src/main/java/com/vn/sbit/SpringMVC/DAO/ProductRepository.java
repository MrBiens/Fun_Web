package com.vn.sbit.SpringMVC.DAO;

import com.vn.sbit.SpringMVC.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
     boolean existsByProductName(String productName);
}
