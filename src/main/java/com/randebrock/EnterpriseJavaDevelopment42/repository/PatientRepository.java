package com.randebrock.EnterpriseJavaDevelopment42.repository;

import com.randebrock.EnterpriseJavaDevelopment42.enums.EmployeeStatus;
import com.randebrock.EnterpriseJavaDevelopment42.model.Patient;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
    Patient save(Patient patient);
    List<Patient> findByBirthdayBetween(LocalDate from, LocalDate to);

    List<Patient> findByAdmittedByDepartment(String department);
    List<Patient> findByAdmittedByEmployeeStatus(EmployeeStatus employeeStatus);

   /*
   Create a route to get all patients with a doctor whose status is OFF.
   reate a route to update patient’s information (the user should be able to
            update any patient information through this route).
*/


}
