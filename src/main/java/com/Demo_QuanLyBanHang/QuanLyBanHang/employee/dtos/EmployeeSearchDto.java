package com.Demo_QuanLyBanHang.QuanLyBanHang.employee.dtos;

public class EmployeeSearchDto {

    private String fullName;
    private String phoneNumber;
    private String email;
    private String gender;

    public EmployeeSearchDto() {}

    public EmployeeSearchDto(String fullName, String phoneNumber, String email, String gender) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.gender = gender;
    }

    // Getters & Setters
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
}
