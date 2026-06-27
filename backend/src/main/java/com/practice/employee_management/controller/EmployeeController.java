package com.practice.employee_management.controller;


import com.practice.employee_management.dto.EmployeeRequest;
import com.practice.employee_management.dto.EmployeeResponse;
import com.practice.employee_management.entity.Employee;
import com.practice.employee_management.exception.ResourceNotFoundException;
import com.practice.employee_management.repository.EmployeeRepository;
import com.practice.employee_management.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeResponse createEmployee(
            @Valid
            @RequestBody EmployeeRequest request){

        return employeeService.createEmployee(request);
    }

    @GetMapping("/{id}")
    public EmployeeResponse getEmployeeById(
            @PathVariable Long id){

        return employeeService.getEmployeeById(id);
    }

    @PutMapping("/{id}")
    public EmployeeResponse updateEmployee(
            @PathVariable Long id,
            @Valid @RequestBody EmployeeRequest request
    ){
        return employeeService.updateEmployee(id,request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable Long id) {

        employeeService.deleteEmployee(id);

    }
    @GetMapping
    public List<EmployeeResponse> getAllEmployees() {

        return employeeService.getAllEmployees();

    }
    @GetMapping("/search")
    public List<EmployeeResponse> searchEmployees(
            @RequestParam String name){

        return employeeService.searchEmployees(name);
    }
    @GetMapping(params = "department")
    public List<EmployeeResponse> getEmployeesByDepartment(
            @RequestParam String department){

        return employeeService.getEmployeesByDepartment(department);
    }

}
