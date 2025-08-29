package com.Demo_QuanLyBanHang.QuanLyBanHang.users.mappers;


import com.Demo_QuanLyBanHang.QuanLyBanHang.users.dtos.request.RoleRequest;
import com.Demo_QuanLyBanHang.QuanLyBanHang.users.dtos.response.RoleResponse;
import com.Demo_QuanLyBanHang.QuanLyBanHang.users.entities.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permission", ignore = true)

    Role toRole(RoleRequest roleRequest);

    RoleResponse toRoleResponse(Role role);
}
