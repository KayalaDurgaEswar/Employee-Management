package com.practice.employee_management.dto;

import com.practice.employee_management.entity.EmployeeStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
@Getter
@Setter
public class EmployeeResponse {
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
