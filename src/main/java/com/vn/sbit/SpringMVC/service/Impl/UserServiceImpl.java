package com.vn.sbit.SpringMVC.service.Impl;

import com.vn.sbit.SpringMVC.repository.RoleRepository;
import com.vn.sbit.SpringMVC.repository.UserRepository;
import com.vn.sbit.SpringMVC.entity.Role;
import com.vn.sbit.SpringMVC.entity.User;
import com.vn.sbit.SpringMVC.enum_project.RoleEnum;
import com.vn.sbit.SpringMVC.service.UserService;
import com.vn.sbit.SpringMVC.dto.request.RegisterUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10);
    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);

    }

    @Override
    public void createUser(RegisterUser registerUser) {
        User user = new User();
        BCryptPasswordEncoder bcrypt= new BCryptPasswordEncoder();

        user.setUsername(registerUser.getUsername());
        user.setPassword(bcrypt.encode(registerUser.getPassword()));
        user.setFirstname(registerUser.getFirstname());
        user.setLastname(registerUser.getLastname());
        user.setEmail(registerUser.getEmail());

        Role role_user = roleRepository.findByName(RoleEnum.ROLE_USER.name());
        Set<Role> roles=new HashSet<>();
        roles.add(role_user);

        user.setRoles(roles);
        user.setEnabled(true);

        userRepository.save(user);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        } else
            System.out.println("Roles for user " + user.getUsername() + ": " + user.getRoles());
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), roleToAuthorities(user.getRoles()));
    }


    private Set<? extends GrantedAuthority> roleToAuthorities(Set<Role> roles){

        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());
        // duyệt (stream) từng roles trong set , mỗi string(vd:role_admin) sẽ trả về 1 SimpleGrantedAuthority mới . Và tập hợp nó thành 1 chuỗi set
    }


}
