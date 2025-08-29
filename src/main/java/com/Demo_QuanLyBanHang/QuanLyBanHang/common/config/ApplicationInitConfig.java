package com.Demo_QuanLyBanHang.QuanLyBanHang.common.config;

import com.Demo_QuanLyBanHang.QuanLyBanHang.common.constants.PredefinedRole;
import com.Demo_QuanLyBanHang.QuanLyBanHang.users.entities.Role;
import com.Demo_QuanLyBanHang.QuanLyBanHang.users.entities.User;
import com.Demo_QuanLyBanHang.QuanLyBanHang.users.repositories.RoleRepository;
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
    private static final String ADMIN_PASSWORD = "123456";

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository, RoleRepository roleRepository) {
        return args -> {
            if(userRepository.findByEmail(ADMIN_EMAIL).isEmpty()) {
                roleRepository.save(Role.builder()
                        .name(PredefinedRole.USER_ROLE)
                        .description("user role")
                        .build());

                Role adminRole = roleRepository.save(Role.builder()
                        .name(PredefinedRole.ADMIN_ROLE)
                        .description("admin role")
                        .build());

                var roles = new HashSet<Role>();

                roles.add(adminRole);

                User user = User.builder()
                        .email(ADMIN_EMAIL)
                        .fullName("Admin")
                        .address("Hồ Chí Minh, Việt Nam")
                        .phoneNumber("0123456789")
                        .password(ADMIN_PASSWORD)
                        .roles(roles)
                        .build();
                userRepository.save(user);
            }
        };
    };
}
