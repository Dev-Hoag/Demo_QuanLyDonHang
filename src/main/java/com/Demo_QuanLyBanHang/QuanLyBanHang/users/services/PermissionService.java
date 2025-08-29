package com.Demo_QuanLyBanHang.QuanLyBanHang.users.services;

import com.Demo_QuanLyBanHang.QuanLyBanHang.users.dtos.request.PermissionRequest;
import com.Demo_QuanLyBanHang.QuanLyBanHang.users.dtos.response.PermissionResponse;
import com.Demo_QuanLyBanHang.QuanLyBanHang.users.entities.Permission;
import com.Demo_QuanLyBanHang.QuanLyBanHang.users.mappers.PermissionMapper;
import com.Demo_QuanLyBanHang.QuanLyBanHang.users.repositories.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@EnableMethodSecurity
public class PermissionService {
    private final PermissionMapper permissionMapper;
    private final PermissionRepository permissionRepository;

    public PermissionResponse createPermission(PermissionRequest permissionRequest) {
        Permission permission = permissionMapper.toPermission(permissionRequest);
        permission = permissionRepository.save(permission);
        return permissionMapper.toPermissionResponse(permission);
    }

    public List<PermissionResponse> getAll() {
        var permissions = permissionRepository.findAll();
        return permissions.stream().map(permissionMapper::toPermissionResponse).toList();
    }

    public void deletePermission(String permissionName) {
        permissionRepository.deleteById(permissionName);
    }
}