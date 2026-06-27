package com.practice.employee_management.controller;


import com.practice.employee_management.dto.EmployeeRequest;
import com.practice.employee_management.dto.EmployeeResponse;
import com.practice.employee_management.entity.Employee;
import com.practice.employee_management.exception.ResourceNotFoundException;
import com.practice.employee_management.repository.EmployeeRepository;
import com.practice.employee_management.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    @Operation(summary = "Create a new Employee")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeResponse createEmployee(
            @Valid
            @RequestBody EmployeeRequest request){

        return employeeService.createEmployee(request);
    }
    @Operation(summary = "Get Employee")
    @GetMapping("/{id}")
    public EmployeeResponse getEmployeeById(
            @PathVariable Long id){

        return employeeService.getEmployeeById(id);
    }
    @Operation(summary = "Update Employee")
    @PutMapping("/{id}")
    public EmployeeResponse updateEmployee(
            @PathVariable Long id,
            @Valid @RequestBody EmployeeRequest request
    ){
        return employeeService.updateEmployee(id,request);
    }
    @Operation(summary = "Delete Employee")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable Long id) {

        employeeService.deleteEmployee(id);

    }
    @Operation(summary = "Get All Employee")
    @GetMapping
    public List<EmployeeResponse> getAllEmployees() {

        return employeeService.getAllEmployees();

    }
    @Operation(summary = "Search Employee")
    @GetMapping("/search")
    public List<EmployeeResponse> searchEmployees(
            @RequestParam String name){

        return employeeService.searchEmployees(name);
    }
    @Operation(summary = "filter by Dept")
    @GetMapping(params = "department")
    public List<EmployeeResponse> getEmployeesByDepartment(
            @RequestParam String department){

        return employeeService.getEmployeesByDepartment(department);
    }
    @GetMapping("/page")
    public Page<EmployeeResponse> getEmployees(
            @RequestParam int page,
            @RequestParam int size){

        return employeeService.getEmployees(page,size);
    }

}
