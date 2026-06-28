package com.practice.employee_management.service;

import com.practice.employee_management.dto.DashboardResponse;
import com.practice.employee_management.entity.EmployeeStatus;
import com.practice.employee_management.repository.EmployeeRepository;
import com.practice.employee_management.service.Impl.DashboardServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class DashboardServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private DashboardServiceImpl dashboardService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnDashboardStatistics() {

        when(employeeRepository.count())
                .thenReturn(10L);

        when(employeeRepository.countByStatus(EmployeeStatus.ACTIVE))
                .thenReturn(8L);

        when(employeeRepository.countByStatus(EmployeeStatus.INACTIVE))
                .thenReturn(2L);

        when(employeeRepository.countDistinctDepartments())
                .thenReturn(4L);

        DashboardResponse response =
                dashboardService.getDashboardStatistics();

        assertEquals(10L, response.getTotalEmployees());
        assertEquals(8L, response.getActiveEmployees());
        assertEquals(2L, response.getInactiveEmployees());
        assertEquals(4L, response.getDepartmentCount());

        verify(employeeRepository).count();
        verify(employeeRepository).countByStatus(EmployeeStatus.ACTIVE);
        verify(employeeRepository).countByStatus(EmployeeStatus.INACTIVE);
        verify(employeeRepository).countDistinctDepartments();
    }
}