package com.habeeb.cycle.resourceserver.services;

import com.habeeb.cycle.resourceserver.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    public List<Employee> getAllEmployee(){
        List<Employee> employees = new ArrayList<>();
        Employee employee1 = new Employee(1, "Habeeb Okunade", "IT", 125000);
        Employee employee2 = new Employee(2, "Raheemat Okunade", "Sales", 105000);
        Employee employee3 = new Employee(3, "Ridwan Kadiri", "Marketing", 120000);
        Employee employee4 = new Employee(4, "Aminat Kadiri", "HR", 110000);
        Employee employee5 = new Employee(5, "Tunde Adesola", "Security", 90000);

        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);
        employees.add(employee4);
        employees.add(employee5);

        return employees;
    }
}
