package com.randebrock.EnterpriseJavaDevelopment42.controller.interfaces;

import com.randebrock.EnterpriseJavaDevelopment42.enums.EmployeeStatus;
import com.randebrock.EnterpriseJavaDevelopment42.model.Employee;
import com.randebrock.EnterpriseJavaDevelopment42.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface PatientController {
    List<Patient> findAll();

    Patient findById(Integer id);
    List<Patient> getByDateOfBirthBetween(LocalDate from, LocalDate to);


    Patient addNewPatient(Patient patient);

    List<Patient> getByAdmittedByDepartment(String department);

    List<Patient> getByAdmittedByEmployeeStatus();


}
