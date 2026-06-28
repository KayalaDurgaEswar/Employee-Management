package com.practice.employee_management.controller;

import com.practice.employee_management.shared.ApiResponse;
import com.practice.employee_management.dto.EmployeeRequest;
import com.practice.employee_management.dto.EmployeeResponse;
import com.practice.employee_management.entity.EmployeeStatus;
import com.practice.employee_management.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<ApiResponse<EmployeeResponse>> createEmployee(
            @Valid @RequestBody EmployeeRequest request) {

        EmployeeResponse response = employeeService.createEmployee(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        ApiResponse.<EmployeeResponse>builder()
                                .success(true)
                                .message("Employee created successfully")
                                .data(response)
                                .build()
                );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EmployeeResponse>> getEmployeeById(
            @PathVariable Long id) {

        EmployeeResponse response = employeeService.getEmployeeById(id);

        return ResponseEntity.ok(
                ApiResponse.<EmployeeResponse>builder()
                        .success(true)
                        .message("Employee fetched successfully")
                        .data(response)
                        .build()
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<EmployeeResponse>>> getAllEmployees() {

        List<EmployeeResponse> response = employeeService.getAllEmployees();

        return ResponseEntity.ok(
                ApiResponse.<List<EmployeeResponse>>builder()
                        .success(true)
                        .message("Employees fetched successfully")
                        .data(response)
                        .build()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<EmployeeResponse>> updateEmployee(
            @PathVariable Long id,
            @Valid @RequestBody EmployeeRequest request) {

        EmployeeResponse response = employeeService.updateEmployee(id, request);

        return ResponseEntity.ok(
                ApiResponse.<EmployeeResponse>builder()
                        .success(true)
                        .message("Employee updated successfully")
                        .data(response)
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteEmployee(
            @PathVariable Long id) {

        employeeService.deleteEmployee(id);

        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .success(true)
                        .message("Employee deleted successfully")
                        .data(null)
                        .build()
        );
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<EmployeeResponse>>> searchEmployees(
            @RequestParam String name) {

        List<EmployeeResponse> response = employeeService.searchEmployees(name);

        return ResponseEntity.ok(
                ApiResponse.<List<EmployeeResponse>>builder()
                        .success(true)
                        .message("Employees fetched successfully")
                        .data(response)
                        .build()
        );
    }

    @GetMapping(params = "department")
    public ResponseEntity<ApiResponse<List<EmployeeResponse>>> getEmployeesByDepartment(
            @RequestParam String department) {

        List<EmployeeResponse> response =
                employeeService.getEmployeesByDepartment(department);

        return ResponseEntity.ok(
                ApiResponse.<List<EmployeeResponse>>builder()
                        .success(true)
                        .message("Employees fetched successfully")
                        .data(response)
                        .build()
        );
    }

    @GetMapping(params = "status")
    public ResponseEntity<ApiResponse<List<EmployeeResponse>>> getEmployeesByStatus(
            @RequestParam EmployeeStatus status) {

        List<EmployeeResponse> response =
                employeeService.getEmployeesByStatus(status);

        return ResponseEntity.ok(
                ApiResponse.<List<EmployeeResponse>>builder()
                        .success(true)
                        .message("Employees fetched successfully")
                        .data(response)
                        .build()
        );
    }

    @GetMapping("/page")
    public ResponseEntity<ApiResponse<Page<EmployeeResponse>>> getEmployeesWithPagination(
            @RequestParam int page,
            @RequestParam int size) {

        Page<EmployeeResponse> response =
                employeeService.getEmployees(page, size);

        return ResponseEntity.ok(
                ApiResponse.<Page<EmployeeResponse>>builder()
                        .success(true)
                        .message("Employees fetched successfully")
                        .data(response)
                        .build()
        );
    }
}