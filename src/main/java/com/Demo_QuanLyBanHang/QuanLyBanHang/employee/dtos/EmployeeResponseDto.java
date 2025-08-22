package com.Demo_QuanLyBanHang.QuanLyBanHang.employee.dtos;

import com.Demo_QuanLyBanHang.QuanLyBanHang.employee.entities.Employee;
import com.Demo_QuanLyBanHang.QuanLyBanHang.employee.entities.Employee.Gender;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class EmployeeResponseDto {

    private UUID employeeId;
    private String fullName;
    private String phoneNumber;
    private String email;
    private LocalDate birthDate;
    private Gender gender;
    private UUID userAccountId;
    private LocalDateTime createdAt;

    public EmployeeResponseDto() {}

    public EmployeeResponseDto(UUID employeeId, String fullName, String phoneNumber, String email,
                               LocalDate birthDate, Gender gender, UUID userAccountId, LocalDateTime createdAt) {
        this.employeeId = employeeId;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.birthDate = birthDate;
        this.gender = gender;
        this.userAccountId = userAccountId;
        this.createdAt = createdAt;
    }

    public static EmployeeResponseDto fromEntity(Employee employee) {
        return new EmployeeResponseDto(
                employee.getEmployeeId(),
                employee.getFullName(),
                employee.getPhoneNumber(),
                employee.getEmail(),
                employee.getBirthDate(),
                employee.getGender(),
                employee.getUserAccountId(),
                employee.getCreatedAt()
        );
    }

    // Getters & Setters
    public UUID getEmployeeId() { return employeeId; }
    public void setEmployeeId(UUID employeeId) { this.employeeId = employeeId; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }
    public Gender getGender() { return gender; }
    public void setGender(Gender gender) { this.gender = gender; }
    public UUID getUserAccountId() { return userAccountId; }
    public void setUserAccountId(UUID userAccountId) { this.userAccountId = userAccountId; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
