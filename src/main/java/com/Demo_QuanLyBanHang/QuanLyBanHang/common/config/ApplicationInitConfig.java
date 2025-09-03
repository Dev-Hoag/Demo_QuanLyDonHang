package com.Demo_QuanLyBanHang.QuanLyBanHang.common.config;

import com.Demo_QuanLyBanHang.QuanLyBanHang.common.enums.Role;
import com.Demo_QuanLyBanHang.QuanLyBanHang.users.entities.User;
import com.Demo_QuanLyBanHang.QuanLyBanHang.users.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.NonFinal;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class ApplicationInitConfig {
    @NonFinal
    private static final String ADMIN_EMAIL = "admin@gmail.com";

    @NonFinal
    private static final String ADMIN_PASSWORD = "1234567890";

    private final UserRepository userRepository;

    @Bean
    ApplicationRunner applicationRunner() {
        return args -> {
            if(userRepository.findByEmail(ADMIN_EMAIL).isEmpty()) {
                Set<Role> roles = new HashSet<>();
                roles.add(Role.ADMIN);

                User user = User.builder()
                        .email(ADMIN_EMAIL)
                        .fullName("Admin")
                        .address("33 Lâm Đình Trúc, TP.Lâm Đồng, tỉnh Phan Thiết")
                        .phoneNumber("0869884725")
                        .roles(roles)
                        .password(ADMIN_PASSWORD)
                        .build();

                userRepository.save(user);
            }
        };
    };
}
