package com.practice.employee_management.service;

import com.practice.employee_management.dto.EmployeeRequest;
import com.practice.employee_management.dto.EmployeeResponse;
import com.practice.employee_management.entity.Employee;
import com.practice.employee_management.exception.ResourceNotFoundException;
import com.practice.employee_management.mapper.EmployeeMapper;
import com.practice.employee_management.repository.EmployeeRepository;
import com.practice.employee_management.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public EmployeeResponse createEmployee(EmployeeRequest request) {

        Employee employee = EmployeeMapper.toEntity(request);

        Employee savedEmployee = employeeRepository.save(employee);

        return EmployeeMapper.toResponse(savedEmployee);
    }

    @Override
    public EmployeeResponse getEmployeeById(Long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Employee not found with id : " + id));

        return EmployeeMapper.toResponse(employee);


    }
    @Override
    public EmployeeResponse updateEmployee(Long id, EmployeeRequest request) {


        Employee employee = employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(" Employee not foudn with id :" + id));


        employee.setEmployeeCode(request.getEmployeeCode());
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setEmail(request.getEmail());
        employee.setDepartment(request.getDepartment());
        employee.setDesignation(request.getDesignation());
        employee.setDateOfJoining(request.getDateOfJoining());
        employee.setSalary(request.getSalary());
        employee.setStatus(request.getStatus());


        Employee updated_employee = employeeRepository.save(employee);


        return EmployeeMapper.toResponse(updated_employee);

    }
    @Override
    public void deleteEmployee(Long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Employee not found with id: " + id));

        employeeRepository.delete(employee);
    }
}