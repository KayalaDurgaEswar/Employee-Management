package com.practice.employee_management.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Employee {
    private Long id;

    private String employeeCode;

    private String firstName;

    private String lastName;

    private String email;

    private String department;

    private String designation;

    private LocalDate dateOfJoining;

    private BigDecimal salary;

    private EmployeeStatus status;
}
