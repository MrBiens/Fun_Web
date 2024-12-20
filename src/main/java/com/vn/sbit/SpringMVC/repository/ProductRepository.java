package com.vn.sbit.SpringMVC.repository;

import com.vn.sbit.SpringMVC.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
     boolean existsByProductName(String productName);
//     boolean existsByProductNameAnd(String productName);

}
