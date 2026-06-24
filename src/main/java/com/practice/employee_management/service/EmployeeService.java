package com.practice.employee_management.service;

import com.practice.employee_management.entity.Employee;

public interface EmployeeService {
    Employee createEmployee(Employee employee);

    Employee getEmployeeById(Long id);
}
