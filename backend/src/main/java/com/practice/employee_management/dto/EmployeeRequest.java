package com.practice.employee_management.dto;

import com.practice.employee_management.entity.EmployeeStatus;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
@Getter
@Setter
public class EmployeeRequest {

    @NotBlank(message = "Employee code is required")
    private String employeeCode;

    @NotBlank(message = "First name is required")
    private String firstName;

    private String lastName;

    @Email(message = "Invalid email")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Department is required")
    private String department;

    private String designation;

    @NotNull(message = "Joining date is required")
    private LocalDate dateOfJoining;

    @Positive(message = "Salary should be greater than zero")
    private BigDecimal salary;

    @NotNull(message = "Status is required")
    private EmployeeStatus status;


}