package com.Demo_QuanLyBanHang.QuanLyBanHang.users.services;
import com.Demo_QuanLyBanHang.QuanLyBanHang.common.constants.PredefinedRole;
import com.Demo_QuanLyBanHang.QuanLyBanHang.common.enums.ErrorCode;
import com.Demo_QuanLyBanHang.QuanLyBanHang.common.exceptions.AppException;
import com.Demo_QuanLyBanHang.QuanLyBanHang.common.utils.AuthUtil;
import com.Demo_QuanLyBanHang.QuanLyBanHang.users.dtos.request.SignUp;
import com.Demo_QuanLyBanHang.QuanLyBanHang.users.dtos.request.UserUpdateRequest;
import com.Demo_QuanLyBanHang.QuanLyBanHang.users.dtos.response.UserResponse;
import com.Demo_QuanLyBanHang.QuanLyBanHang.users.entities.Role;
import com.Demo_QuanLyBanHang.QuanLyBanHang.users.entities.User;
import com.Demo_QuanLyBanHang.QuanLyBanHang.users.mappers.UserMapper;
import com.Demo_QuanLyBanHang.QuanLyBanHang.users.repositories.RoleRepository;
import com.Demo_QuanLyBanHang.QuanLyBanHang.users.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final UserMapper userMapper;

    public User createUser(SignUp request){
        String email = request.getEmail().toLowerCase();

        String phoneNumber = request.getPhoneNumber().toLowerCase();

        boolean existsByEmail = userRepository.existsByEmail(email);

        boolean existsByPhoneNumber = userRepository.existsByPhoneNumber(phoneNumber);

        if(existsByEmail || existsByPhoneNumber){
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        User user = userMapper.toUser(request);
        HashSet<Role> roles = new HashSet<>();
        roleRepository.findById(PredefinedRole.USER_ROLE).ifPresent(roles::add);
        user.setRoles(roles);

        return userRepository.save(user);
    }

    public UserResponse getMyInfo(){

        UUID userid = AuthUtil.getUserIdFromContext();

        User user = this.userRepository.findById(userid)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        return this.userMapper.toUserResponse(user);
    }

    public User findByIdOrThrow(UUID userId){
        return userRepository.findById(userId)
                .orElseThrow(()->new AppException(ErrorCode.USER_NOT_FOUND));
    }

    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public void deleteUser(UUID userId){
        userRepository.deleteById(userId);
    }

    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public UserResponse updateUser(UUID userId, UserUpdateRequest updateRequest){
        User user = findByIdOrThrow(userId);
        userMapper.updateUser(user, updateRequest);
        var roles = roleRepository.findAllById(updateRequest.getRoles());
        user.setRoles(new HashSet<>(roles));
        return userMapper.toUserResponse(user);
    }

    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public UserResponse getUserProfile(UUID userId){
        User user = findByIdOrThrow(userId);
        return userMapper.toUserResponse(user);
    }

    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public List<UserResponse> getAllUsers(){
        return userRepository.findAll().stream().map(user -> userMapper.toUserResponse(user)).toList();
    }
}
