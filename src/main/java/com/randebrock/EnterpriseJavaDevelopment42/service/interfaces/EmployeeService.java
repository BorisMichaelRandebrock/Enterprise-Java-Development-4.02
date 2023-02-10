package com.randebrock.EnterpriseJavaDevelopment42.service.interfaces;


import com.randebrock.EnterpriseJavaDevelopment42.controller.dto.EmployeeStatusDTO;
import com.randebrock.EnterpriseJavaDevelopment42.model.Employee;

public interface EmployeeService {

    void updateEmployeesStatus(Integer id, EmployeeStatusDTO employeeStatusDTO);
    void updateDepartment(Integer id, String department);
}
