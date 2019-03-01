package com.habeeb.cycle.resourceserver.controllers;

import com.habeeb.cycle.resourceserver.model.Employee;
import com.habeeb.cycle.resourceserver.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/user/employees")
    public List<Employee> getEmployeeList(){
        return employeeService.getAllEmployee();
    }
}
