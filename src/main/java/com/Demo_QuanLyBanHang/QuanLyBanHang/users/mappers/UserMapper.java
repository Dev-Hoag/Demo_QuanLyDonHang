package com.Demo_QuanLyBanHang.QuanLyBanHang.users.mappers;


import com.Demo_QuanLyBanHang.QuanLyBanHang.users.dtos.request.SignUp;
import com.Demo_QuanLyBanHang.QuanLyBanHang.users.dtos.request.UserUpdateRequest;
import com.Demo_QuanLyBanHang.QuanLyBanHang.users.dtos.response.UserResponse;
import com.Demo_QuanLyBanHang.QuanLyBanHang.users.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(SignUp userCreationRequest);

    UserResponse toUserResponse(User user);

    @Mapping(target = "roles", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdateRequest userUpdateRequest);
}
