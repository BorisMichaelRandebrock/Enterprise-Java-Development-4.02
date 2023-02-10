package com.randebrock.EnterpriseJavaDevelopment42.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.randebrock.EnterpriseJavaDevelopment42.controller.dto.EmployeeDepartmentDTO;
import com.randebrock.EnterpriseJavaDevelopment42.controller.dto.EmployeeStatusDTO;
import com.randebrock.EnterpriseJavaDevelopment42.enums.EmployeeStatus;
import com.randebrock.EnterpriseJavaDevelopment42.model.Employee;
import com.randebrock.EnterpriseJavaDevelopment42.repository.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerImplTest {
    @Autowired
    private EmployeeRepository employeeRepository;

//    @Autowired
//    private WebApplicationContext webApplicationContext; // Vamos a poder trabajar con los controladores
    @Autowired
    private MockMvc mockMvc; // Simular las peticiones HTTP
    private final ObjectMapper objectMapper = new ObjectMapper(); // Construir objetos de JSON a partir de clases de JAVA

    private Employee employee1, employee2;

    @BeforeEach
    void setUp() {
//        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

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
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());

        assertTrue(mvcResult.getResponse().getContentAsString().contains("health"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("qwe"));
    }

    @Test
    void findById_EnterId_FindsEmployee() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/employees/" + employee1.getId()))
                .andExpect(status().isOk()) // Validar el status code de respuesta = OK (200)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());

        assertTrue(mvcResult.getResponse().getContentAsString().contains("health"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("qwe"));
        assertTrue(!mvcResult.getResponse().getContentAsString().contains("qwe"));

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
    void addNewEmployee_EnteringNewEmployee_ReturnsNewEmployee() throws Exception {
        Employee employee3 = new Employee(1235434, "healthh", "Booboo", EmployeeStatus.ON);
        String body = objectMapper.writeValueAsString(employee3);

        MvcResult mvcResult = mockMvc.perform(
                        post("/employees")
                                .content(body)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("Booboo"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("healthh"));
        assertTrue(employeeRepository.existsById(employee3.getId()));
    }

    @Test
    void addAnotherEmployee_NotherMethodToEnterEmp_VoidNewEmploye() throws Exception {
        Employee employee3 = new Employee(1235434, "healthh", "Booboo", EmployeeStatus.ON);
        String body = objectMapper.writeValueAsString(employee3);

        MvcResult mvcResult = mockMvc.perform(
                        post("/employees")
                                .content(body)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("Booboo"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("healthh"));
        assertTrue(employeeRepository.existsById(employee3.getId()));
    }

    @Test
    void updateEmployeesStatus() throws Exception {
        EmployeeStatusDTO employeeStatusDTO = new EmployeeStatusDTO();
        employeeStatusDTO.setStatus(EmployeeStatus.ON);
        String body =  objectMapper.writeValueAsString(employeeStatusDTO);

        MvcResult mvcResult = mockMvc.perform(
                        patch("/employees/status/" + employee2.getId())
                                .content(body)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNoContent())
                .andReturn();

        assertTrue(employeeRepository.existsById(employee2.getId()));
        assertEquals(employeeStatusDTO.getStatus(),employeeRepository.findById(456).get().getAdmitted_by());
    }

    @Test
    void updateDepartment_EnterANewDepartment_ChangesTheEmployeesDepartment() throws Exception {
        EmployeeDepartmentDTO employeeDepartmentDTO = new EmployeeDepartmentDTO();
        employeeDepartmentDTO.setDepartment("pulmonary");
        String body =  objectMapper.writeValueAsString(employeeDepartmentDTO);

        MvcResult mvcResult = mockMvc.perform(
                        put("/employees/department/" + employee2.getId())
                                .content(body)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNoContent())
                .andReturn();


        System.out.println(employee2.getDepartment());
        assertTrue(employeeRepository.existsById(employee2.getId()));
        assertEquals("pulmonary",employeeRepository.findById(456).get().getDepartment());
    }

//    @Test
//    void addAnotherEmployee() throws Exception {
//        Employee employee3 = new Employee(1235434, "healthh", "Booboo", EmployeeStatus.ON);
//        String body = objectMapper.writeValueAsString(employee3);
//
//        MvcResult mvcResult = mockMvc.perform(
//                        post("/employees"+employee3)
//                                .content(body)
//                                .contentType(MediaType.APPLICATION_JSON)
//                )
//                .andExpect(status().isCreated())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andReturn();
//
////        assertTrue(mvcResult.getResponse().getContentAsString().contains("Booboo"));
////        assertTrue(mvcResult.getResponse().getContentAsString().contains("healthh"));
////        assertTrue(employeeRepository.existsById(employee3.getId()));
//    }
}