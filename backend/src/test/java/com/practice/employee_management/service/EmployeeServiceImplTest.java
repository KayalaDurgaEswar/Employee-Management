package com.practice.employee_management.service;

import com.practice.employee_management.dto.EmployeeRequest;
import com.practice.employee_management.dto.EmployeeResponse;
import com.practice.employee_management.entity.Employee;
import com.practice.employee_management.entity.EmployeeStatus;
import com.practice.employee_management.exception.DuplicateResourceException;
import com.practice.employee_management.exception.ResourceNotFoundException;
import com.practice.employee_management.mapper.EmployeeMapper;
import com.practice.employee_management.repository.EmployeeRepository;
import com.practice.employee_management.service.Impl.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee employee;
    private EmployeeRequest request;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);

        request = new EmployeeRequest();
        request.setEmployeeCode("EMP001");
        request.setFirstName("Durga");
        request.setLastName("Eswar");
        request.setEmail("durga@gmail.com");
        request.setDepartment("Engineering");
        request.setDesignation("Software Engineer");
        request.setDateOfJoining(LocalDate.now());
        request.setSalary(BigDecimal.valueOf(70000));
        request.setStatus(EmployeeStatus.ACTIVE);

        employee = EmployeeMapper.toEntity(request);
        employee.setId(1L);
    }

    @Test
    void shouldCreateEmployee() {

        when(employeeRepository.existsByEmail(request.getEmail()))
                .thenReturn(false);

        when(employeeRepository.existsByEmployeeCode(request.getEmployeeCode()))
                .thenReturn(false);

        when(employeeRepository.save(any(Employee.class)))
                .thenReturn(employee);

        EmployeeResponse response = employeeService.createEmployee(request);

        assertNotNull(response);
        assertEquals("EMP001", response.getEmployeeCode());
        assertEquals("Durga", response.getFirstName());

        verify(employeeRepository).save(any(Employee.class));
    }

    @Test
    void shouldThrowExceptionWhenEmailAlreadyExists() {

        when(employeeRepository.existsByEmail(request.getEmail()))
                .thenReturn(true);

        DuplicateResourceException exception =
                assertThrows(DuplicateResourceException.class,
                        () -> employeeService.createEmployee(request));

        assertEquals("Email already exists.", exception.getMessage());

        verify(employeeRepository, never()).save(any(Employee.class));
    }

    @Test
    void shouldGetEmployeeById() {

        when(employeeRepository.findById(1L))
                .thenReturn(Optional.of(employee));

        EmployeeResponse response =
                employeeService.getEmployeeById(1L);

        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Durga", response.getFirstName());

        verify(employeeRepository).findById(1L);
    }

    @Test
    void shouldThrowExceptionWhenEmployeeNotFound() {

        when(employeeRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> employeeService.getEmployeeById(1L));

        verify(employeeRepository).findById(1L);
    }

    @Test
    void shouldUpdateEmployee() {

        when(employeeRepository.findById(1L))
                .thenReturn(Optional.of(employee));

        when(employeeRepository.existsByEmail(anyString()))
                .thenReturn(false);

        when(employeeRepository.existsByEmployeeCode(anyString()))
                .thenReturn(false);

        when(employeeRepository.save(any(Employee.class)))
                .thenReturn(employee);

        request.setDesignation("Senior Software Engineer");

        EmployeeResponse response =
                employeeService.updateEmployee(1L, request);

        assertNotNull(response);

        verify(employeeRepository).save(any(Employee.class));
    }

    @Test
    void shouldDeleteEmployee() {

        when(employeeRepository.findById(1L))
                .thenReturn(Optional.of(employee));

        doNothing().when(employeeRepository).delete(employee);

        employeeService.deleteEmployee(1L);

        verify(employeeRepository).delete(employee);
    }

    @Test
    void shouldSearchEmployees() {

        when(employeeRepository.findByFirstNameContainingIgnoreCase("Durga"))
                .thenReturn(List.of(employee));

        List<EmployeeResponse> employees =
                employeeService.searchEmployees("Durga");

        assertEquals(1, employees.size());
        assertEquals("Durga", employees.get(0).getFirstName());

        verify(employeeRepository)
                .findByFirstNameContainingIgnoreCase("Durga");
    }

    @Test
    void shouldGetEmployeesByDepartment() {

        when(employeeRepository.findByDepartment("Engineering"))
                .thenReturn(List.of(employee));

        List<EmployeeResponse> employees =
                employeeService.getEmployeesByDepartment("Engineering");

        assertEquals(1, employees.size());

        verify(employeeRepository)
                .findByDepartment("Engineering");
    }

    @Test
    void shouldGetEmployeesByStatus() {

        when(employeeRepository.findByStatus(EmployeeStatus.ACTIVE))
                .thenReturn(List.of(employee));

        List<EmployeeResponse> employees =
                employeeService.getEmployeesByStatus(EmployeeStatus.ACTIVE);

        assertEquals(1, employees.size());

        verify(employeeRepository)
                .findByStatus(EmployeeStatus.ACTIVE);
    }

}