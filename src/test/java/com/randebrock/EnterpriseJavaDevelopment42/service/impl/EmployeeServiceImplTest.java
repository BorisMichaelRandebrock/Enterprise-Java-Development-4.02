package com.randebrock.EnterpriseJavaDevelopment42.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.randebrock.EnterpriseJavaDevelopment42.controller.dto.EmployeeDepartmentDTO;
import com.randebrock.EnterpriseJavaDevelopment42.controller.dto.EmployeeStatusDTO;
import com.randebrock.EnterpriseJavaDevelopment42.enums.EmployeeStatus;
import com.randebrock.EnterpriseJavaDevelopment42.model.Employee;
import com.randebrock.EnterpriseJavaDevelopment42.repository.EmployeeRepository;
import com.randebrock.EnterpriseJavaDevelopment42.service.interfaces.EmployeeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeServiceImplTest {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private EmployeeService employeeService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private Employee employee1, employee2;



    @BeforeEach
    void setUp() {
        employee1 = new Employee(123, "healthh", "Boo", EmployeeStatus.ON);
        employee2 = new Employee(456, "qwe", "asdasd", EmployeeStatus.OFF);

        employeeRepository.saveAll(List.of(employee1, employee2));
    }

    @AfterEach
    void tearDown() {
        employeeRepository.deleteAll();
    }
    @Test
    void updateEmployeesStatus_UpdatingEmployeeStatus_ChangesEmployeeStatus() throws Exception {
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
    void updateDepartment_SettingNewDepartment_FindsNewDepartment() throws Exception {
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

    @Test
    void testUpdateDepartment_NotExistingEmployee_ThrowsNewError() throws Exception {
        EmployeeDepartmentDTO employeeDepartmentDTO = new EmployeeDepartmentDTO();
        employeeDepartmentDTO.setDepartment("pulmonary");
        String body =  objectMapper.writeValueAsString(employeeDepartmentDTO);

        MvcResult mvcResult = mockMvc.perform(
                        put("/employees/department/1234677890" )
                                .content(body)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNotFound())
                .andReturn();
        assertTrue(mvcResult.getResolvedException().getMessage().contains("Employee"));
        assertEquals("Employee not found", mvcResult.getResponse().getErrorMessage());

    }
}