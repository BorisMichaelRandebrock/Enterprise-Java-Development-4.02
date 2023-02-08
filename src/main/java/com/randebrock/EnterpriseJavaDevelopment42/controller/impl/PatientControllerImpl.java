package com.randebrock.EnterpriseJavaDevelopment42.controller.impl;

import com.randebrock.EnterpriseJavaDevelopment42.controller.interfaces.PatientController;
import com.randebrock.EnterpriseJavaDevelopment42.enums.EmployeeStatus;
import com.randebrock.EnterpriseJavaDevelopment42.model.Employee;
import com.randebrock.EnterpriseJavaDevelopment42.model.Patient;
import com.randebrock.EnterpriseJavaDevelopment42.repository.PatientRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
public class PatientControllerImpl implements PatientController {
    @Autowired
    private PatientRepository patientRepository;
    @GetMapping("/patients")
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    @GetMapping("/patients/{id}")
    public Patient findById(@PathVariable(name = "id") Integer id) {
        return patientRepository.findById(id).get();
    }

    @GetMapping("/patients/dob")
    public List<Patient> getByDateOfBirthBetween(@RequestParam LocalDate from,@RequestParam LocalDate to) {
        return patientRepository.findByBirthdayBetween(from, to);
    }

    @PostMapping("/patients")
    @ResponseStatus(HttpStatus.CREATED)
    public Patient addNewPatient(@RequestBody @Valid Patient patient) {
        return patientRepository.save(patient);
    }

    @GetMapping("/patients/admittedByDepartment/{department}")
    public List<Patient> getByAdmittedByDepartment(@PathVariable(name = "department") String department) {
        return patientRepository.findByAdmittedByDepartment(department);
    }

    @GetMapping("/patients/admittedByStatusOFF")
    public List<Patient> getByAdmittedByEmployeeStatus() {
        return patientRepository.findByAdmittedByEmployeeStatus(EmployeeStatus.OFF);
    }

    /*
    *   @GetMapping("/patients/admittedByStatus")
    public List<Patient> getByAdmittedByEmployeeStatus() {
        return patientRepository.findByAdmittedByEmployeeStatus(@RequestBody EmployeeStatus empployeeStatus);
    }
    * */


}
