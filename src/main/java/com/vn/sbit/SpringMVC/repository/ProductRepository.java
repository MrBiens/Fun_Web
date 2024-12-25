package com.vn.sbit.SpringMVC.repository;

import com.vn.sbit.SpringMVC.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
     boolean existsByProductName(String productName);

     @Query("SELECT p FROM Product p WHERE p.productName LIKE %?1%")
     List<Product> findAllByProductNameIgnoreCase(String productName);

}
