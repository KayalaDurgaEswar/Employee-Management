package com.practice.employee_management.controller;

import com.practice.employee_management.dto.DashboardResponse;
import com.practice.employee_management.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {
 private final DashboardService dashboardService;

    @GetMapping
    public DashboardResponse getDashboardStatistics() {

        return dashboardService.getDashboardStatistics();
    }
}
