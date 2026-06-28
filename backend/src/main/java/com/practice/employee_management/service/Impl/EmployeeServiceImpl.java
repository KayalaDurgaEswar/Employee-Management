package com.practice.employee_management.service.Impl;

import com.practice.employee_management.dto.EmployeeRequest;
import com.practice.employee_management.dto.EmployeeResponse;
import com.practice.employee_management.entity.Employee;
import com.practice.employee_management.entity.EmployeeStatus;
import com.practice.employee_management.exception.DuplicateResourceException;
import com.practice.employee_management.exception.ResourceNotFoundException;
import com.practice.employee_management.mapper.EmployeeMapper;
import com.practice.employee_management.repository.EmployeeRepository;
import com.practice.employee_management.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public EmployeeResponse createEmployee(EmployeeRequest request) {
        if(employeeRepository.existsByEmail(request.getEmail())){

            throw new DuplicateResourceException(
                    "Email already exists.");

        }
        if(employeeRepository.existsByEmployeeCode(request.getEmployeeCode())){

            throw new DuplicateResourceException(
                    "Employee Code already exists.");

        }
        Employee employee = EmployeeMapper.toEntity(request);

        Employee savedEmployee = employeeRepository.save(employee);
        log.info("Creating employee with code {}", request.getEmployeeCode());
        return EmployeeMapper.toResponse(savedEmployee);
    }

    @Override
    public EmployeeResponse getEmployeeById(Long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Employee not found with id : " + id));
        log.info("Fetching employee with id {}", id);
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
        log.info("Updating employee with id {}", id);

        return EmployeeMapper.toResponse(updated_employee);

    }
    @Override
    public void deleteEmployee(Long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Employee not found with id: " + id));
        log.info("Deleting Employee by Id{}",id);
        employeeRepository.delete(employee);
    }
    @Override
    public List<EmployeeResponse> getAllEmployees() {

        List<Employee> employees = employeeRepository.findAll();

        return employees.stream()
                .map(EmployeeMapper::toResponse)
                .toList();
    }
    @Override
    public List<EmployeeResponse> searchEmployees(String name) {

        return employeeRepository
                .findByFirstNameContainingIgnoreCase(name)
                .stream()
                .map(EmployeeMapper::toResponse)
                .toList();
    }
    @Override
    public List<EmployeeResponse> getEmployeesByDepartment(String department) {

        return employeeRepository
                .findByDepartment(department)
                .stream()
                .map(EmployeeMapper::toResponse)
                .toList();
    }
    public Page<EmployeeResponse> getEmployees(int page, int size) {

        log.info("Fetching employees page {} with size {}", page, size);

        Pageable pageable = PageRequest.of(page, size);

        return employeeRepository
                .findAll(pageable)
                .map(EmployeeMapper::toResponse);
    }
    @Override
    public List<EmployeeResponse> getEmployeesByStatus(EmployeeStatus status) {

        log.info("Fetching employees with status {}", status);

        return employeeRepository.findByStatus(status)
                .stream()
                .map(EmployeeMapper::toResponse)
                .toList();
    }

}