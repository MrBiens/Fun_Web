package com.vn.sbit.SpringMVC.DAO;

import com.vn.sbit.SpringMVC.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    public User findByUsername(String username);

}
