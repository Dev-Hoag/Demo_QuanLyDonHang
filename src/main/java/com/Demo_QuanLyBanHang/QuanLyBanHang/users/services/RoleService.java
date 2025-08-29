package com.Demo_QuanLyBanHang.QuanLyBanHang.users.services;

import com.Demo_QuanLyBanHang.QuanLyBanHang.users.dtos.request.RoleRequest;
import com.Demo_QuanLyBanHang.QuanLyBanHang.users.dtos.response.RoleResponse;
import com.Demo_QuanLyBanHang.QuanLyBanHang.users.mappers.RoleMapper;
import com.Demo_QuanLyBanHang.QuanLyBanHang.users.repositories.PermissionRepository;
import com.Demo_QuanLyBanHang.QuanLyBanHang.users.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@EnableMethodSecurity
public class RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;
    private final PermissionRepository permissionRepository;

    public RoleResponse create(RoleRequest roleRequest) {
        var roles = roleMapper.toRole(roleRequest);
        var permission = permissionRepository.findAllById(roleRequest.getPermissions());
        roles.setPermission(new HashSet<>(permission));
        return roleMapper.toRoleResponse(roleRepository.save(roles));
    }

    public List<RoleResponse> getAll() {
        var roles = roleRepository.findAll();
        return roles.stream().map(roleMapper::toRoleResponse).toList();
    }

    public void deleteRole(String role) {
        roleRepository.deleteById(role);
    }
}
