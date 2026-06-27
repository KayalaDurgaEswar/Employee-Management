package com.practice.employee_management.service;

import com.practice.employee_management.dto.EmployeeRequest;
import com.practice.employee_management.dto.EmployeeResponse;
import com.practice.employee_management.entity.Employee;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {
    EmployeeResponse createEmployee(EmployeeRequest request);

    EmployeeResponse getEmployeeById(Long id);
    EmployeeResponse updateEmployee(Long id, EmployeeRequest request);
    void deleteEmployee(Long id);
    List<EmployeeResponse> getAllEmployees();
    List<EmployeeResponse> searchEmployees(String name);
    List<EmployeeResponse> getEmployeesByDepartment(String department);
    Page<EmployeeResponse> getEmployees(int page, int size);
}
