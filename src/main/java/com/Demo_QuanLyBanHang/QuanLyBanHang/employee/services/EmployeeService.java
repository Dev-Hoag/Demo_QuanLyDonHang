package com.Demo_QuanLyBanHang.QuanLyBanHang.employee.services;

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

    public EmployeeResponseDto createEmployee(EmployeeCreateDto createDto) {
        if (employeeRepository.existsByPhoneNumber(createDto.getPhoneNumber())) {
            throw new RuntimeException("Số điện thoại đã tồn tại trong hệ thống");
        }

        if (createDto.getEmail() != null && employeeRepository.findByEmail(createDto.getEmail()).isPresent()) {
            throw new RuntimeException("Email đã tồn tại trong hệ thống");
        }

        Employee employee = new Employee();
        employee.setFullName(createDto.getFullName().trim());
        employee.setPhoneNumber(createDto.getPhoneNumber() != null ? createDto.getPhoneNumber().trim() : null);
        employee.setEmail(createDto.getEmail().trim());
        employee.setBirthDate(createDto.getBirthDate());
        employee.setGender(createDto.getGender());
        employee.setUserAccountId(createDto.getUserAccountId());

        Employee saved = employeeRepository.save(employee);
        return EmployeeResponseDto.fromEntity(saved);
    }

    @Transactional(readOnly = true)
    public EmployeeResponseDto getEmployeeById(UUID id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy nhân viên với ID: " + id));
        return EmployeeResponseDto.fromEntity(employee);
    }

    @Transactional(readOnly = true)
    public EmployeeResponseDto getEmployeeByPhoneNumber(String phoneNumber) {
        Employee employee = employeeRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy nhân viên với số điện thoại: " + phoneNumber));
        return EmployeeResponseDto.fromEntity(employee);
    }

    public EmployeeResponseDto updateEmployee(UUID id, EmployeeCreateDto updateDto) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy nhân viên với ID: " + id));

        if (!employee.getPhoneNumber().equals(updateDto.getPhoneNumber()) &&
            employeeRepository.existsByPhoneNumber(updateDto.getPhoneNumber())) {
            throw new RuntimeException("Số điện thoại đã tồn tại trong hệ thống");
        }

        employee.setFullName(updateDto.getFullName().trim());
        employee.setPhoneNumber(updateDto.getPhoneNumber());
        employee.setEmail(updateDto.getEmail());
        employee.setBirthDate(updateDto.getBirthDate());
        employee.setGender(updateDto.getGender());
        employee.setUserAccountId(updateDto.getUserAccountId());

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
                .filter(e -> (searchDto.getFullName() == null || e.getFullName().contains(searchDto.getFullName())))
                .filter(e -> (searchDto.getPhoneNumber() == null || e.getPhoneNumber().contains(searchDto.getPhoneNumber())))
                .filter(e -> (searchDto.getEmail() == null || e.getEmail().contains(searchDto.getEmail())))
                .filter(e -> (searchDto.getGender() == null || e.getGender().equals(searchDto.getGender())))
                .map(EmployeeResponseDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public boolean existsByPhoneNumber(String phoneNumber) {
        return employeeRepository.existsByPhoneNumber(phoneNumber);
    }

    @Transactional(readOnly = true)
    public Optional<EmployeeResponseDto> findEmployeeByPhoneNumber(String phoneNumber) {
        return employeeRepository.findByPhoneNumber(phoneNumber)
                .map(EmployeeResponseDto::fromEntity);
    }
}
