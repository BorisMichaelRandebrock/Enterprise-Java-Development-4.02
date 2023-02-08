package com.randebrock.EnterpriseJavaDevelopment42.controller.impl;

import com.randebrock.EnterpriseJavaDevelopment42.controller.dto.EmployeeStatusDTO;
import com.randebrock.EnterpriseJavaDevelopment42.controller.interfaces.EmployeeController;
import com.randebrock.EnterpriseJavaDevelopment42.enums.EmployeeStatus;
import com.randebrock.EnterpriseJavaDevelopment42.model.Employee;
import com.randebrock.EnterpriseJavaDevelopment42.repository.EmployeeRepository;
import com.randebrock.EnterpriseJavaDevelopment42.service.interfaces.EmployeerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeControllerImpl implements EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeerService employeerService;



    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @GetMapping("/employees/{id}")
    public Employee findById(@PathVariable(name = "id") Integer id) {
        return employeeRepository.findById(id).get();
    }

    @GetMapping("/employees/status/{status}")
    public List<Employee> findByStatus(@PathVariable("status")EmployeeStatus status){
        return employeeRepository.findByEmployeeStatus(status);
    }

    @PostMapping("employees")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee addNewEmployee(@RequestBody @Valid Employee employee) {
        return employeeRepository.save(employee);
    }

    @PostMapping("employee-add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addAnotherEmployee(@RequestBody @Valid Employee employee) {
        employeeRepository.save(employee);
//        employeerService.save(employee);

    }

   /* @Override
    public void updateEmployeesStatus(Integer id, Enum status, EmployeeStatusDTO employeeStatusDTO) {

    }*/

    @PatchMapping("/employees/{id}/status")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateEmployeesStatus(@PathVariable Integer id, @RequestBody @Valid EmployeeStatusDTO employeeStatusDTO) {

        employeerService.updateEmployeesStatus(id, employeeStatusDTO);
    }

    @PutMapping("/employees/department/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateDepartment(@PathVariable String id, @RequestBody @Valid Employee employee) {
        employeerService.updateDepartment(id, employee);
    }

    /*
    *  @PutMapping("/courses/{code}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204 Everything salio bien, no te devuelvo nada :D
    public void updateDepartment(@PathVariable String code, @RequestBody @Valid Course course) {
        // Toda la lógica de negocio fue movida al SERVICIO
        courserService.update(code, course);
    }
    * */

//    @Override  .updateStatus(id, employeeStatusDTO);
//    public void updateEmployeesStatus(Integer id, Enum status, EmployeeStatusDTO employeeStatusDTO) {
//        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
//    }

}
