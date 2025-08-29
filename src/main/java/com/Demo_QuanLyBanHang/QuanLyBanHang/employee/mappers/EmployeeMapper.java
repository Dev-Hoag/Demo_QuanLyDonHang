package com.Demo_QuanLyBanHang.QuanLyBanHang.employee.mappers;

import com.Demo_QuanLyBanHang.QuanLyBanHang.employee.dtos.EmployeeCreateDto;
import com.Demo_QuanLyBanHang.QuanLyBanHang.employee.dtos.EmployeeResponseDto;
import com.Demo_QuanLyBanHang.QuanLyBanHang.employee.entities.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    Employee toEmployee(EmployeeCreateDto employeeCreateDto);

    EmployeeResponseDto toEmployeeResponseDto(Employee employee);
}
