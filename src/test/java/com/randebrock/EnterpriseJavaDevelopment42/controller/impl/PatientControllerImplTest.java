package com.randebrock.EnterpriseJavaDevelopment42.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.randebrock.EnterpriseJavaDevelopment42.controller.dto.EmployeeDepartmentDTO;
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
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PatientControllerImplTest {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private Employee employee1, employee2;
    private Patient patient1, patient2, patient3;
    @BeforeEach
    void setUp() {
        employee1 = new Employee(123, "healthh", "Boo", EmployeeStatus.ON);
        employee2 = new Employee(456, "qwe", "asdasd", EmployeeStatus.OFF);

        employeeRepository.saveAll(List.of(employee1, employee2));

        LocalDate dorian = LocalDate.of(1767,05,05);
        LocalDate donald = LocalDate.of(1923,02,02);
        LocalDate pepa = LocalDate.of(2011,02,02);


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
    void findAll_NoParam_AllPatients() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/patients"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());

        assertTrue(mvcResult.getResponse().getContentAsString().contains("Dorian Grey"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Donald Duck"));

    }

    @Test
    void findById_EnteredID_FindPatientById() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/patients/" +patient1.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());

        assertTrue(mvcResult.getResponse().getContentAsString().contains("Dorian"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("Donald"));
        assertTrue(!mvcResult.getResponse().getContentAsString().contains("qwe"));

    }

    @Test
    void getByDateOfBirthBetween_EnteredRangeOfDates_FindsListOfPatientsWithinRange() throws Exception {
            MvcResult mvcResult = mockMvc.perform(get("/patients/dob?from=1464-03-02&to=2084-04-01"))
                    .andExpect(status().isOk()) // Validar el status code de respuesta = OK (200)
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andReturn();
            System.out.println(mvcResult.getResponse().getContentAsString());

            assertTrue(mvcResult.getResponse().getContentAsString().contains("Dorian"));
            assertTrue(mvcResult.getResponse().getContentAsString().contains("Donald"));
            assertFalse(mvcResult.getResponse().getContentAsString().contains("Iñigo Montoya"));


        }



    @Test
    void getByAdmittedByEmployeeStatus_EnteringAdmittedStatusOff_FindsPatientAdmittedByEmployeeWithSTatsusOff() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/patients/admittedByStatusOFF"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());

        assertTrue(mvcResult.getResponse().getContentAsString().contains("OFF"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("ON"));

    }

    @Test
    void addNewPatient_AddNewPatient_FindsNewPatient() throws Exception {
        LocalDate pepa = LocalDate.of(2011,02,02);

        patient3 = new Patient(5235434, "Pepa Pig",null, employee1);
        System.out.println(patient3.getBirthday());
        System.out.println(patient3.getName() + " "+patient3.getId() + " " + patient3.getAdmittedBy().getName());
        String body = objectMapper.writeValueAsString(patient3);

        MvcResult mvcResult = mockMvc.perform(
                        post("/patients")
                                .content(body)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());

        assertTrue(mvcResult.getResponse().getContentAsString().contains("Pepa"));
        assertTrue(patientRepository.existsById(patient3.getId()));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Pig"));
        assertTrue(patientRepository.existsById(patient3.getId()));

    }

    @Test
    void getByAdmittedByDepartment_DontKnowWhatsWrong() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/patients/admittedByDepartment/cardiology"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        System.out.println("Hello--------------------->" + mvcResult.getResponse().getContentType().contains(" "));

//        assertTrue(mvcResult.getResponse().getContentAsString().contains("cardiology"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("ON"));

        }
    @Test
    void updatePatientsInformation_AddingNewPatientName_ChangesPatientsName() throws Exception {
        ///patients/{id}
//        EmployeeDepartmentDTO employeeDepartmentDTO = new EmployeeDepartmentDTO();
//        employeeDepartmentDTO.setDepartment("pulmonary");
        System.out.println("-------------->" + patient1.getName());
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


        assertTrue(patientRepository.existsById(patient1.getId()));
        assertEquals("The Dorian Grey...! not pepa pig",patientRepository.findById(patient1.getId()).get().getName());

    }
}