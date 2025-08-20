package com.Demo_QuanLyBanHang.QuanLyBanHang.guest.repositories;

import com.Demo_QuanLyBanHang.QuanLyBanHang.guest.entities.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {
    
    // Tìm theo số điện thoại
    Optional<Guest> findByPhoneNumber(String phoneNumber);
    
    // Kiểm tra số điện thoại đã tồn tại chưa
    boolean existsByPhoneNumber(String phoneNumber);
    
    // Tìm theo email
    Optional<Guest> findByEmail(String email);
    
    // Tìm theo loại khách hàng
    List<Guest> findByGuestType(Guest.GuestType guestType);
    
    // Tìm kiếm theo tên (LIKE)
    @Query("SELECT g FROM Guest g WHERE g.fullName LIKE %:fullName% AND g.isActive = true")
    List<Guest> findByFullNameContaining(@Param("fullName") String fullName);
    
    // Tìm kiếm theo số điện thoại (LIKE)
    @Query("SELECT g FROM Guest g WHERE g.phoneNumber LIKE %:phoneNumber% AND g.isActive = true")
    List<Guest> findByPhoneNumberContaining(@Param("phoneNumber") String phoneNumber);
    
    // Tìm kiếm tổng hợp
    @Query("SELECT g FROM Guest g WHERE " +
           "(:fullName IS NULL OR g.fullName LIKE %:fullName%) AND " +
           "(:phoneNumber IS NULL OR g.phoneNumber LIKE %:phoneNumber%) AND " +
           "(:email IS NULL OR g.email LIKE %:email%) AND " +
           "(:address IS NULL OR g.address LIKE %:address%) AND " +
           "g.isActive = true")
    List<Guest> searchGuests(@Param("fullName") String fullName,
                            @Param("phoneNumber") String phoneNumber,
                            @Param("email") String email,
                            @Param("address") String address);
    
    // Tìm tất cả khách hàng đang active
    List<Guest> findByIsActiveTrue();
} 