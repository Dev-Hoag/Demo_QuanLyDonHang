package com.Demo_QuanLyBanHang.QuanLyBanHang.employee.repositories;

import com.Demo_QuanLyBanHang.QuanLyBanHang.users.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.Demo_QuanLyBanHang.QuanLyBanHang.employee.entities.Employee;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    boolean existsByUser(User user);
}
