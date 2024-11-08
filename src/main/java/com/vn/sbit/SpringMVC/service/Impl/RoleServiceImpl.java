package com.vn.sbit.SpringMVC.service.Impl;

import com.vn.sbit.SpringMVC.repository.RoleRepository;
import com.vn.sbit.SpringMVC.dto.request.RoleRequest;
import com.vn.sbit.SpringMVC.entity.Role;
import com.vn.sbit.SpringMVC.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    @Override
    public void createRole(RoleRequest request) {
        Role role = new Role();
        role.setName(request.getName());
        roleRepository.save(role);

    }

    @Override
    public void deleteRole() {

    }
}
