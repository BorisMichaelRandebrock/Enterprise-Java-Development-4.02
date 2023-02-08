package com.randebrock.EnterpriseJavaDevelopment42.service.impl;

import com.randebrock.EnterpriseJavaDevelopment42.model.Patient;
import com.randebrock.EnterpriseJavaDevelopment42.repository.PatientRepository;
import com.randebrock.EnterpriseJavaDevelopment42.service.interfaces.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public void updatePatientsInformation(Integer id, Patient patient) {
        Optional<Patient> optionalPatient = patientRepository.findById(patient.getId());
        patientRepository.save(patient);

    }
}
