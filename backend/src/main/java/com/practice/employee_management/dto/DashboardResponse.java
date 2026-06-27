package com.practice.employee_management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor

public class DashboardResponse {
    private Long totalEmployees;
    private Long activeEmployees;
    private Long inactiveEmployees;
    private Long departmentCount;
}
