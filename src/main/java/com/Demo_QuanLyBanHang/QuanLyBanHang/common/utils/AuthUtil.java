package com.Demo_QuanLyBanHang.QuanLyBanHang.common.utils;

import com.Demo_QuanLyBanHang.QuanLyBanHang.common.enums.ErrorCode;
import com.Demo_QuanLyBanHang.QuanLyBanHang.common.exceptions.AppException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.UUID;

public class AuthUtil {
    public static UUID getUserIdFromContext(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || !(authentication.getPrincipal() instanceof Jwt jwt)){
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }
        return UUID.fromString(jwt.getClaim("userId"));
    }
}
