package com.practice.employee_management.service;

import com.practice.employee_management.dto.EmployeeRequest;
import com.practice.employee_management.dto.EmployeeResponse;
import com.practice.employee_management.entity.Employee;

public interface EmployeeService {
    EmployeeResponse createEmployee(EmployeeRequest request);

    EmployeeResponse getEmployeeById(Long id);
    EmployeeResponse updateEmployee(Long id, EmployeeRequest request);
    void deleteEmployee(Long id);
}
