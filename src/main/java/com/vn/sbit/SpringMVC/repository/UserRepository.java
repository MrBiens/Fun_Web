package com.vn.sbit.SpringMVC.repository;

import com.vn.sbit.SpringMVC.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);

    User findByEmail(String email);

    User findByVerifyCode(String verifyCode);
}
