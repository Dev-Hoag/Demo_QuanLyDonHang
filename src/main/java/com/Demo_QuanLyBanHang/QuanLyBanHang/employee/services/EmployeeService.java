package com.Demo_QuanLyBanHang.QuanLyBanHang.employee.services;

import com.Demo_QuanLyBanHang.QuanLyBanHang.common.enums.ErrorCode;
import com.Demo_QuanLyBanHang.QuanLyBanHang.common.exceptions.AppException;
import com.Demo_QuanLyBanHang.QuanLyBanHang.employee.mappers.EmployeeMapper;
import com.Demo_QuanLyBanHang.QuanLyBanHang.users.entities.User;
import com.Demo_QuanLyBanHang.QuanLyBanHang.users.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Demo_QuanLyBanHang.QuanLyBanHang.employee.dtos.EmployeeCreateDto;
import com.Demo_QuanLyBanHang.QuanLyBanHang.employee.dtos.EmployeeResponseDto;
import com.Demo_QuanLyBanHang.QuanLyBanHang.employee.dtos.EmployeeSearchDto;
import com.Demo_QuanLyBanHang.QuanLyBanHang.employee.entities.Employee;
import com.Demo_QuanLyBanHang.QuanLyBanHang.employee.repositories.EmployeeRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeResponseDto createEmployee(EmployeeCreateDto createDto) {

        User user = userRepository.findById(createDto.getUserAccountId())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        if(employeeRepository.existsByUser(user)) {
            throw new AppException(ErrorCode.EMPLOYEE_ALREADY_EXISTS);
        }

        Employee employee = employeeMapper.toEmployee(createDto);
        employee.setUser(user);
        Employee result =  employeeRepository.save(employee);
        return employeeMapper.toEmployeeResponseDto(result);
    }

    @Transactional(readOnly = true)
    public EmployeeResponseDto getEmployeeById(UUID id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy nhân viên với ID: " + id));
        return EmployeeResponseDto.fromEntity(employee);
    }

    public EmployeeResponseDto updateEmployee(UUID id, EmployeeCreateDto updateDto) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy nhân viên với ID: " + id));

        if (!employee.getUser().getUserId().equals(updateDto.getUserAccountId())) {
            throw new AppException(ErrorCode.USER_ASSIGNMENT_CANNOT_BE_CHANGED);
        }

        employee.setBirthDate(updateDto.getBirthDate());

        employee.setGender(updateDto.getGender());

        Employee updated = employeeRepository.save(employee);

        return EmployeeResponseDto.fromEntity(updated);
    }

    public void deleteEmployee(UUID id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy nhân viên với ID: " + id));
        employeeRepository.delete(employee);
    }

    @Transactional(readOnly = true)
    public List<EmployeeResponseDto> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(EmployeeResponseDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<EmployeeResponseDto> searchEmployees(EmployeeSearchDto searchDto) {
        return employeeRepository.findAll().stream()
                .filter(e -> searchDto.getFullName() == null
                        || e.getUser().getFullName().toLowerCase().contains(searchDto.getFullName().toLowerCase()))
                .filter(e -> searchDto.getPhoneNumber() == null
                        || e.getUser().getPhoneNumber().contains(searchDto.getPhoneNumber()))
                .filter(e -> searchDto.getEmail() == null
                        || e.getUser().getEmail().toLowerCase().contains(searchDto.getEmail().toLowerCase()))
                .filter(e -> searchDto.getGender() == null
                        || e.getGender().name().equalsIgnoreCase(searchDto.getGender()))
                .map(employeeMapper::toEmployeeResponseDto)
                .collect(Collectors.toList());
    }

}
