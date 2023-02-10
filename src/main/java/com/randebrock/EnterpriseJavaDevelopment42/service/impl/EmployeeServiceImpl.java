package com.randebrock.EnterpriseJavaDevelopment42.service.impl;


import com.randebrock.EnterpriseJavaDevelopment42.controller.dto.EmployeeStatusDTO;
import com.randebrock.EnterpriseJavaDevelopment42.model.Employee;
import com.randebrock.EnterpriseJavaDevelopment42.repository.EmployeeRepository;
import com.randebrock.EnterpriseJavaDevelopment42.service.interfaces.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public void updateEmployeesStatus(Integer id,  EmployeeStatusDTO employeeStatusDTO) {

        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        optionalEmployee.get().setAdmitted_by(employeeStatusDTO.getStatus());
        employeeRepository.save(optionalEmployee.get());
    }

    @Override
    public void updateDepartment(Integer id, String department) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (!optionalEmployee.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This employee is not found.. :(");
        } else {
        Employee employeeUpdated = optionalEmployee.get();
            employeeUpdated.setDepartment(department);
       employeeRepository.save(employeeUpdated);
        }
    }

}
