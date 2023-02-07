package com.randebrock.EnterpriseJavaDevelopment42.controller.impl;

import com.randebrock.EnterpriseJavaDevelopment42.controller.interfaces.PatientController;
import com.randebrock.EnterpriseJavaDevelopment42.model.Employee;
import com.randebrock.EnterpriseJavaDevelopment42.model.Patient;
import com.randebrock.EnterpriseJavaDevelopment42.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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

//    @GetMapping("patients/{startDate}{endDate}")
//    public List<Patient> findPatientStartDateBetween(@PathVariable(name = "startDate") Date startDate,@PathVariable(name = "endDate") Date endDate) {
//        return patientRepository.findPatientStartDateBetween(startDate, endDate);
//    }

   /* public Patient findById(@PathVariable(name = "id") Integer id) {
        return patientRepository.findById(id).get();
    }*/
}
