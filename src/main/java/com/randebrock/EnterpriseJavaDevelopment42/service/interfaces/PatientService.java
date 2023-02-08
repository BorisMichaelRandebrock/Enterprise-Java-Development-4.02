package com.randebrock.EnterpriseJavaDevelopment42.service.interfaces;

import com.randebrock.EnterpriseJavaDevelopment42.model.Patient;

public interface PatientService {
    void updatePatientsInformation(Integer id, Patient patient);
}
