package com.Demo_QuanLyBanHang.QuanLyBanHang.users.repositories;
import com.Demo_QuanLyBanHang.QuanLyBanHang.users.entities.InvalidatedToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvalidatedTokenRepository extends JpaRepository<InvalidatedToken, String> {
}
