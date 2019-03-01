package com.habeeb.cycle.resourceserver.controllers;

import com.habeeb.cycle.resourceserver.model.Employee;
import com.habeeb.cycle.resourceserver.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/user/getEmployeeList")
    public List<Employee> getEmployeeList(){
        return employeeService.getAllEmployee();
    }
}
