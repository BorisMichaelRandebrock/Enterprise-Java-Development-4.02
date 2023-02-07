package com.randebrock.EnterpriseJavaDevelopment42.repository;

import com.randebrock.EnterpriseJavaDevelopment42.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
//    List<Patient> findPatientStartDateBetween(Date startDate, Date endDate);

}
