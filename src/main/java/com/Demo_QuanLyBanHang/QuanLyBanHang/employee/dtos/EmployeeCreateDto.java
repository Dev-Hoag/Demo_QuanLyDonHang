package com.Demo_QuanLyBanHang.QuanLyBanHang.employee.dtos;

import com.Demo_QuanLyBanHang.QuanLyBanHang.employee.entities.Employee.Gender;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.UUID;

public class EmployeeCreateDto {

    @NotBlank(message = "Họ tên không được để trống")
    @Size(min = 2, max = 100, message = "Họ tên phải từ 2 đến 100 ký tự")
    private String fullName;

    @Pattern(regexp = "^(0|84)(3[2-9]|5[689]|7[06-9]|8[1-689]|9[0-46-9])[0-9]{7}$",
             message = "Số điện thoại không hợp lệ")
    private String phoneNumber;

    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không hợp lệ")
    private String email;

    private LocalDate birthDate;

    @NotNull(message = "Giới tính không được để trống")
    private Gender gender;

    @NotNull(message = "User account id không được để trống")
    private UUID userAccountId;

    public EmployeeCreateDto() {}

    public EmployeeCreateDto(String fullName, String phoneNumber, String email,
                             LocalDate birthDate, Gender gender, UUID userAccountId) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.birthDate = birthDate;
        this.gender = gender;
        this.userAccountId = userAccountId;
    }

    // Getters & Setters
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
}
