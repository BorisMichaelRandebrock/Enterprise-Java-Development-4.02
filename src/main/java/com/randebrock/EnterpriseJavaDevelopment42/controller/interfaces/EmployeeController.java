package com.randebrock.EnterpriseJavaDevelopment42.controller.interfaces;

import com.randebrock.EnterpriseJavaDevelopment42.enums.EmployeeStatus;
import com.randebrock.EnterpriseJavaDevelopment42.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeController {
    List<Employee> findAll();

    Optional<Employee> findById(Integer id);

    Optional<Employee> findByStatus(EmployeeStatus status);

}
/*
* public interface CourseController {
    List<Course> findAll();

}
*

Create a route to get a doctor by employee_id.
Create a route to get doctors by status.
Create a route to get doctors by department.
Create a route to get all patients.
Create a route to get a patient by patient_id.
Create a route to get patients date of birth within a specified range.
Create a route to get patients by the department that their admitting doctor is in (For example, get all patients admitted by a doctor in cardiology).
Create a route to get all patients with a doctor whose status is OFF.

* */