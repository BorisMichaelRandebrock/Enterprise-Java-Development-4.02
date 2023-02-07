package com.randebrock.EnterpriseJavaDevelopment42.controller.impl;

import com.randebrock.EnterpriseJavaDevelopment42.controller.interfaces.EmployeeController;
import com.randebrock.EnterpriseJavaDevelopment42.enums.EmployeeStatus;
import com.randebrock.EnterpriseJavaDevelopment42.model.Employee;
import com.randebrock.EnterpriseJavaDevelopment42.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeControllerImpl implements EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @GetMapping("/employees/{id}")
    public Employee findById(@PathVariable(name = "id") Integer id) {
        return employeeRepository.findById(id).get();
    }

    @GetMapping("/employees/status/{status}")
    public List<Employee> findByStatus(@PathVariable("status")EmployeeStatus status){
        return employeeRepository.findByEmployeeStatus(status);
    }

}
