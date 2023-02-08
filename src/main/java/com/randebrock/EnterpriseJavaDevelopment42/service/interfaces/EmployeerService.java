package com.randebrock.EnterpriseJavaDevelopment42.service.interfaces;


import com.randebrock.EnterpriseJavaDevelopment42.controller.dto.EmployeeStatusDTO;
import com.randebrock.EnterpriseJavaDevelopment42.enums.EmployeeStatus;
import com.randebrock.EnterpriseJavaDevelopment42.model.Employee;

public interface EmployeerService {
//    void update(Enum status,  Employee employee);
   /* void updateName(int id, EmployeeStatusDTO employeeStatusDTO);
    void delete(String code);*/

//    void updateEmployeesStatus(Integer id, Enum status, EmployeeStatusDTO employeeStatusDTO);

    void updateEmployeesStatus(Integer id, EmployeeStatusDTO employeeStatusDTO);
    void updateDepartment(String department, Employee employee);
}
