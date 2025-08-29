package com.Demo_QuanLyBanHang.QuanLyBanHang.employee.dtos;

import com.Demo_QuanLyBanHang.QuanLyBanHang.employee.entities.Employee.Gender;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeCreateDto {

    private LocalDate birthDate;

    @NotNull(message = "Giới tính không được để trống")
    private Gender gender;

    @NotNull(message = "User account id không được để trống")
    private UUID userAccountId;

}
