package com.randebrock.EnterpriseJavaDevelopment42.controller.interfaces;

import com.randebrock.EnterpriseJavaDevelopment42.model.Employee;
import com.randebrock.EnterpriseJavaDevelopment42.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;
import java.util.List;

public interface PatientController {
    List<Patient> findAll();

    Patient findById(Integer id);

//    List<Patient> findPatientStartDateBetween(Date startDate, Date endDate);

//       List<Patient> findPatientStartDateBetween(Date startDate, Date endDate);
}
/*
* public interface CourseController {
    List<Course> findAll();

}
*


Create a route to get a patient by patient_id.
Create a route to get patients date of birth within a specified range.
Create a route to get patients by the department that their admitting doctor is in (For example, get all patients admitted by a doctor in cardiology).
Create a route to get all patients with a doctor whose status is OFF.

* */