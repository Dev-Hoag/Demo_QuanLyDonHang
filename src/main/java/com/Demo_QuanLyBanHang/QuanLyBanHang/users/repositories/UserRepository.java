package com.Demo_QuanLyBanHang.QuanLyBanHang.users.repositories;
import com.Demo_QuanLyBanHang.QuanLyBanHang.users.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>{

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    Optional<User> findByPhoneNumber(String phoneNumber);

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByEmailOrPhoneNumber(String email, String phoneNumber);
}
