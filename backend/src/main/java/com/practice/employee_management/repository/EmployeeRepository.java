package com.practice.employee_management.repository;

import com.practice.employee_management.entity.Employee;
import com.practice.employee_management.entity.EmployeeStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByFirstNameContainingIgnoreCase(String firstName);
    List<Employee> findByDepartment(String department);
    List<Employee> findByStatus(EmployeeStatus status);
    long countByStatus(EmployeeStatus status);
    @Query("SELECT COUNT(DISTINCT e.department) FROM Employee e")
    long countDistinctDepartments();
}
