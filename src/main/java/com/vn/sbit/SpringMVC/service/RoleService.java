package com.vn.sbit.SpringMVC.service;


import com.vn.sbit.SpringMVC.dto.request.RoleRequest;

public interface RoleService {
    public void createRole(RoleRequest request);

    public void deleteRole();
}
