package com.practice.employee_management.mapper;

import com.practice.employee_management.dto.EmployeeRequest;
import com.practice.employee_management.entity.Employee;
import com.practice.employee_management.dto.EmployeeRequest;
import com.practice.employee_management.dto.EmployeeResponse;
import com.practice.employee_management.entity.Employee;
public class EmployeeMapper {

    public static Employee toEntity(EmployeeRequest request){

        Employee employee = new Employee();

        employee.setEmployeeCode(request.getEmployeeCode());
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setEmail(request.getEmail());
        employee.setDepartment(request.getDepartment());
        employee.setDesignation(request.getDesignation());
        employee.setDateOfJoining(request.getDateOfJoining());
        employee.setSalary(request.getSalary());
        employee.setStatus(request.getStatus());

        return employee;
    }

    public static EmployeeResponse toResponse(Employee employee) {

        EmployeeResponse response = new EmployeeResponse();

        response.setId(employee.getId());
        response.setEmployeeCode(employee.getEmployeeCode());
        response.setFirstName(employee.getFirstName());
        response.setLastName(employee.getLastName());
        response.setEmail(employee.getEmail());
        response.setDepartment(employee.getDepartment());
        response.setDesignation(employee.getDesignation());
        response.setDateOfJoining(employee.getDateOfJoining());
        response.setSalary(employee.getSalary());
        response.setStatus(employee.getStatus());

        return response;
    }

}
