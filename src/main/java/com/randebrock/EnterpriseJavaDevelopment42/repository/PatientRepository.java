package com.randebrock.EnterpriseJavaDevelopment42.repository;

import com.randebrock.EnterpriseJavaDevelopment42.enums.EmployeeStatus;
import com.randebrock.EnterpriseJavaDevelopment42.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
    Patient save(Patient patient);
    List<Patient> findByBirthdayBetween(LocalDate from, LocalDate to);

    List<Patient> findByAdmittedByDepartment(String department);
    List<Patient> findByAdmittedByEmployeeStatus(EmployeeStatus employeeStatus);



}
