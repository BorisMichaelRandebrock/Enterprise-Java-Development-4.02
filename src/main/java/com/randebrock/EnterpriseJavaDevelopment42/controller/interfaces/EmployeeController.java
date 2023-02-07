package com.randebrock.EnterpriseJavaDevelopment42.controller.interfaces;

import com.randebrock.EnterpriseJavaDevelopment42.enums.EmployeeStatus;
import com.randebrock.EnterpriseJavaDevelopment42.model.Employee;

import java.util.List;

public interface EmployeeController {
    List<Employee> findAll();

    Employee findById(Integer id);

    List<Employee> findByStatus(EmployeeStatus status);

}
