package com.randebrock.EnterpriseJavaDevelopment42.controller.interfaces;

import com.randebrock.EnterpriseJavaDevelopment42.model.Patient;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
@RestController
public interface PatientController {
    List<Patient> findAll();

    Patient findById(Integer id);
    List<Patient> getByDateOfBirthBetween(LocalDate from, LocalDate to);


    Patient addNewPatient(Patient patient);

    List<Patient> getByAdmittedByDepartment(String department);

    List<Patient> getByAdmittedByEmployeeStatus();

    void updatePatientsInformation(Integer id, Patient patient);
}
