package com.vn.sbit.SpringMVC.repository;

import com.vn.sbit.SpringMVC.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
//    @Query("SELECT COUNT(c)>0 FROM Customer c WHERE c.fullName = :customerName")
//    boolean existsByFullName (@Param("customerName") String customerName);

    boolean existsByFullName(String fullName);
}
