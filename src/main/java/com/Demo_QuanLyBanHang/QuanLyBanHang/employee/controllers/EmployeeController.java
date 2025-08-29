package com.Demo_QuanLyBanHang.QuanLyBanHang.employee.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Demo_QuanLyBanHang.QuanLyBanHang.employee.dtos.EmployeeCreateDto;
import com.Demo_QuanLyBanHang.QuanLyBanHang.employee.dtos.EmployeeResponseDto;
import com.Demo_QuanLyBanHang.QuanLyBanHang.employee.dtos.EmployeeSearchDto;
import com.Demo_QuanLyBanHang.QuanLyBanHang.employee.services.EmployeeService;
import java.util.Map;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class EmployeeController {

    private final EmployeeService employeeService;

    // POST /api/employees
    @PostMapping
    public ResponseEntity<?> createEmployee(@Valid @RequestBody EmployeeCreateDto createDto) {
        try {
            EmployeeResponseDto response = employeeService.createEmployee(createDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    // GET /api/employees/{id}
    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable UUID id) {
        try {
            EmployeeResponseDto response = employeeService.getEmployeeById(id);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    // PUT /api/employees/{id}
    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(
            @PathVariable UUID id,
            @Valid @RequestBody EmployeeCreateDto updateDto) {
        try {
            EmployeeResponseDto response = employeeService.updateEmployee(id, updateDto);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    // DELETE /api/employees/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable UUID id) {
        try {
            employeeService.deleteEmployee(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    // GET /api/employees
    @GetMapping
    public ResponseEntity<List<EmployeeResponseDto>> getAllEmployees() {
        List<EmployeeResponseDto> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    // GET /api/employees/search
    @GetMapping("/search")
    public ResponseEntity<List<EmployeeResponseDto>> searchEmployees(
            @RequestParam(required = false) String fullName,
            @RequestParam(required = false) String phoneNumber,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String gender) {

        EmployeeSearchDto searchDto = new EmployeeSearchDto(fullName, phoneNumber, email, gender);
        List<EmployeeResponseDto> employees = employeeService.searchEmployees(searchDto);
        return ResponseEntity.ok(employees);
    }
}
