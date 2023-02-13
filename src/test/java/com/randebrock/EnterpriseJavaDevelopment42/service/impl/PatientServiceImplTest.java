package com.randebrock.EnterpriseJavaDevelopment42.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.randebrock.EnterpriseJavaDevelopment42.controller.dto.EmployeeDepartmentDTO;
import com.randebrock.EnterpriseJavaDevelopment42.controller.dto.EmployeeStatusDTO;
import com.randebrock.EnterpriseJavaDevelopment42.controller.dto.PatientsDTO;
import com.randebrock.EnterpriseJavaDevelopment42.enums.EmployeeStatus;
import com.randebrock.EnterpriseJavaDevelopment42.model.Employee;
import com.randebrock.EnterpriseJavaDevelopment42.model.Patient;
import com.randebrock.EnterpriseJavaDevelopment42.repository.EmployeeRepository;
import com.randebrock.EnterpriseJavaDevelopment42.repository.PatientRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PatientServiceImplTest {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();


    private Employee employee1, employee2;
    private Patient patient1, patient2,patient12;

    @BeforeEach
    void setUp() {
        employee1 = new Employee(123, "healthh", "Boo", EmployeeStatus.ON);
        employee2 = new Employee(456, "qwe", "asdasd", EmployeeStatus.OFF);

        employeeRepository.saveAll(List.of(employee1, employee2));

//        LocalDate dorian = LocalDate.ofEpochDay(1523-05-05);
//        LocalDate donald = LocalDate.ofEpochDay(1923-02-02);

        LocalDate dorian = LocalDate.of(1523,05,05);
        LocalDate donald = LocalDate.of(1923,02,02);


        patient12 = new Patient(123454, "Dorian Grey",null, employee1);
        patient1 = new Patient(1234, "Dorian Grey",dorian, employee1);

        patient2 = new Patient(6751352, "Donald Duck",donald, employee2);

        patientRepository.saveAll(List.of(patient1,patient2));
    }

    @AfterEach
    void tearDown() {
        patientRepository.deleteAll();
        employeeRepository.deleteAll();
    }


    @Test
    void updatePatientsInformation_ChangingPatientName_DBChangesPatientName() throws Exception {
//        PatientsDTO patientsDTO = new PatientsDTO();
//        patientsDTO.setName("boboo grey");
        patient12.setName("boobooo Grey");
        String body =  objectMapper.writeValueAsString(patient12);
        System.out.println(patient12.getName());

        System.out.println(patient12.getBirthday() + " " + patient12.getName());
        MvcResult mvcResult = mockMvc.perform(
                        put("/patients/"+ patient12.getId())
                                .content(body)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNoContent())
                .andReturn();

        System.out.println(patient12.getName());
        assertTrue(patientRepository.existsById(patient12.getId()));
        assertEquals("boobooo Grey",patientRepository.findById(123454).get().getName());


    }

    @Test
    void testUpdatePatientsInformation_ChangingInfoOfPatient_DBInputChangesInfo() throws Exception {
        System.out.println("-------------->" + patient1.getName() + " " + patient1.getBirthday());
        patient1.setName("The Dorian Grey...! not pepa pig");
        patient1.setBirthday(null);
        String body =  objectMapper.writeValueAsString(patient1);

        MvcResult mvcResult = mockMvc.perform(
                        put("/patients/" + patient1.getId())
                                .content(body)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNoContent())
                .andReturn();

        System.out.println(patient1.getName() + " " + patient1.getBirthday());
        assertTrue(patientRepository.existsById(patient1.getId()));
        assertEquals("The Dorian Grey...! not pepa pig",patientRepository.findById(patient1.getId()).get().getName());

    }
}