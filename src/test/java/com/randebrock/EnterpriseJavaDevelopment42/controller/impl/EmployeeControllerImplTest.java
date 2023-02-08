package com.randebrock.EnterpriseJavaDevelopment42.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.randebrock.EnterpriseJavaDevelopment42.enums.EmployeeStatus;
import com.randebrock.EnterpriseJavaDevelopment42.model.Employee;
import com.randebrock.EnterpriseJavaDevelopment42.repository.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class EmployeeControllerImplTest {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private WebApplicationContext webApplicationContext; // Vamos a poder trabajar con los controladores
    private MockMvc mockMvc; // Simular las peticiones HTTP
    private final ObjectMapper objectMapper = new ObjectMapper(); // Construir objetos de JSON a partir de clases de JAVA

    private Employee employee1, employee2;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        employee1 = new Employee(123, "healthh", "Boo", EmployeeStatus.ON);
        employee2 = new Employee(456, "qwe", "asdasd", EmployeeStatus.OFF);

        employeeRepository.saveAll(List.of(employee1, employee2));

    }

    @AfterEach
    void tearDown() {
        employeeRepository.deleteAll();
    }

    @Test
    void findAll_NoParam_AllEmployees() throws Exception {

        MvcResult mvcResult = mockMvc.perform(get("/employees"))
                .andExpect(status().isOk()) // Validar el status code de respuesta = OK (200)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());

        assertTrue(mvcResult.getResponse().getContentAsString().contains("health"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("qwe"));
    }

    @Test
    void findById_EnterId_FindsEmployee() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/employees/123"))
                .andExpect(status().isOk()) // Validar el status code de respuesta = OK (200)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());

        assertTrue(mvcResult.getResponse().getContentAsString().contains("health"));
       assertFalse(mvcResult.getResponse().getContentAsString().contains("qwe"));
    }

    @Test
    void findByStatus_SearchesByStatus_FindsStatusON() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/employees/status/ON"))
                .andExpect(status().isOk()) // Validar el status code de respuesta = OK (200)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());

        assertTrue(mvcResult.getResponse().getContentAsString().contains("health"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("qwe"));

    }

    @Test
    void addNewEmployee() {
    }

    @Test
    void addAnotherEmployee() {
    }

    @Test
    void updateEmployeesStatus() {
    }

    @Test
    void updateDepartment() {
    }
}