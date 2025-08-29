package com.Demo_QuanLyBanHang.QuanLyBanHang.users.mappers;


import com.Demo_QuanLyBanHang.QuanLyBanHang.users.dtos.request.PermissionRequest;
import com.Demo_QuanLyBanHang.QuanLyBanHang.users.dtos.response.PermissionResponse;
import com.Demo_QuanLyBanHang.QuanLyBanHang.users.entities.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {

    Permission toPermission(PermissionRequest permission);

    PermissionResponse toPermissionResponse(Permission permission);
}
