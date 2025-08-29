package com.Demo_QuanLyBanHang.QuanLyBanHang.users.repositories;

import com.Demo_QuanLyBanHang.QuanLyBanHang.users.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
}
