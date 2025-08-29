package com.Demo_QuanLyBanHang.QuanLyBanHang.users.repositories;
import com.Demo_QuanLyBanHang.QuanLyBanHang.users.entities.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, String> {
}
