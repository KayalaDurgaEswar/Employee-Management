package com.practice.employee_management.service;

import com.practice.employee_management.dto.DashboardResponse;
import com.practice.employee_management.entity.EmployeeStatus;
import com.practice.employee_management.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {
    private final EmployeeRepository employeeRepository;
    public DashboardResponse getDashboardStatistics(){
    long totalEmployees = employeeRepository.count();

    long activeEmployees =
            employeeRepository.countByStatus(EmployeeStatus.ACTIVE);

    long inactiveEmployees =
            employeeRepository.countByStatus(EmployeeStatus.INACTIVE);

    long departmentCount =
            employeeRepository.countDistinctDepartments();
    return new DashboardResponse(
            totalEmployees,
            activeEmployees,
            inactiveEmployees,
            departmentCount
    );

}
}