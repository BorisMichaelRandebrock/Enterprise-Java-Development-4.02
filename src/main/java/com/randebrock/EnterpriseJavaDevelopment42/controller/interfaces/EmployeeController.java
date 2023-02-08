package com.randebrock.EnterpriseJavaDevelopment42.controller.interfaces;

import com.randebrock.EnterpriseJavaDevelopment42.controller.dto.EmployeeStatusDTO;
import com.randebrock.EnterpriseJavaDevelopment42.enums.EmployeeStatus;
import com.randebrock.EnterpriseJavaDevelopment42.model.Employee;

import java.util.List;

public interface EmployeeController {
    List<Employee> findAll();

    Employee findById(Integer id);

    List<Employee> findByStatus(EmployeeStatus status);

    Employee addNewEmployee(Employee employee);
    void addAnotherEmployee(Employee employee);

    void updateEmployeesStatus(Integer id,/* Enum status,*/ EmployeeStatusDTO employeeStatusDTO);

}

/*
Create a route to change a doctor’s status.
Create a route to update a doctor’s department.
Create a route to update patient’s information (the user should be able to
update any patient information through this route).

*/