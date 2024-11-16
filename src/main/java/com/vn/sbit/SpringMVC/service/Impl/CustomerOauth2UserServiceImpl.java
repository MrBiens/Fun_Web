package com.vn.sbit.SpringMVC.service.Impl;

import com.vn.sbit.SpringMVC.entity.Role;
import com.vn.sbit.SpringMVC.entity.User;
import com.vn.sbit.SpringMVC.enum_project.RoleEnum;
import com.vn.sbit.SpringMVC.repository.RoleRepository;
import com.vn.sbit.SpringMVC.repository.UserRepository;
import com.vn.sbit.SpringMVC.security.CustomOAuth2User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
public class CustomerOauth2UserServiceImpl extends DefaultOAuth2UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public CustomerOauth2UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }



    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

//        OAuth2User oauth2User = super.loadUser(userRequest);
//        // Lấy thông tin người dùng từ Google
//        String email = oauth2User.getAttribute("email");
//        String name = oauth2User.getAttribute("name");
//        String firstName = oauth2User.getAttribute("given_name");  // First name from Google
//        String lastName = oauth2User.getAttribute("family_name");  // Last name from Google
//
//
//        // Kiểm tra xem người dùng đã tồn tại trong cơ sở dữ liệu chưa
//        User user = userRepository.findByEmail(email);
//        if (user == null) {
//            // Nếu người dùng chưa tồn tại, tạo mới và lưu vào DB
//            user = new User();
//            Role role_user = roleRepository.findByName(RoleEnum.ROLE_USER.name());
//            Set<Role> roles=new HashSet<>();
//            roles.add(role_user);
//            user.setUsername(email);
//            user.setEmail(email);
//            user.setUsername(name);
//            user.setRoles(roles);
//            user.setFirstname(firstName);
//            user.setLastname(lastName);
//            user.setEnabled(true);
//
//
//            userRepository.save(user);
//        }

        return new CustomOAuth2User(super.loadUser(userRequest));
    }

}
