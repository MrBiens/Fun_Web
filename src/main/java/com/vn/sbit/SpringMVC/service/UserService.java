package com.vn.sbit.SpringMVC.service;

import com.vn.sbit.SpringMVC.entity.User;
import com.vn.sbit.SpringMVC.dto.request.RegisterUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService  {
    public User findByUsername(String username);
    public User getUserByEmail(String email);
    public void createUser(RegisterUser registerUser);

}
