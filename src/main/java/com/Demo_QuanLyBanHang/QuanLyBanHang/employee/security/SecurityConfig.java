// package com.Demo_QuanLyBanHang.QuanLyBanHang.guest.security;
// import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.web.SecurityFilterChain;

// @Configuration
// @EnableWebSecurity
// public class SecurityConfig {

//     /**
//      * Security sẽ chỉ bật nếu "spring.security.enabled=true" 
//      * trong application.properties (mặc định = true).
//      * 
//      * Nếu "spring.security.enabled=false" → app chạy không có bảo mật.
//      */
//     @Bean
//     @ConditionalOnProperty(
//             prefix = "spring.security",
//             name = "enabled",
//             havingValue = "true",
//             matchIfMissing = true
//     )
//     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//         http
//             .csrf().disable()
//             .authorizeHttpRequests(auth -> auth
//                 // Cho phép Employee API không cần token
//                 .requestMatchers("/employee/**").permitAll()
//                 // Các API User cần bảo mật
//                 .requestMatchers("/user/**").authenticated()
//                 // Những request khác thì chặn
//                 .anyRequest().denyAll()
//             );
//         return http.build();
//     }

//     /**
//      * Nếu muốn tắt Security hoàn toàn (cho Employee app)
//      * thì set spring.security.enabled=false trong application.properties
//      */
// }
