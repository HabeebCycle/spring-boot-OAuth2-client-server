package com.habeeb.cycle.clientapplication.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class EmployeeController {

    @GetMapping("/client/employees")
    public ModelAndView getEmployees(){
        return new ModelAndView("employees");
    }

    @GetMapping("/")
    public String home(){
        return "employees";
    }
}
