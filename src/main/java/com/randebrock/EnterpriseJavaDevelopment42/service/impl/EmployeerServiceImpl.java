package com.randebrock.EnterpriseJavaDevelopment42.service.impl;


import com.randebrock.EnterpriseJavaDevelopment42.controller.dto.EmployeeStatusDTO;
import com.randebrock.EnterpriseJavaDevelopment42.model.Employee;
import com.randebrock.EnterpriseJavaDevelopment42.repository.EmployeeRepository;
import com.randebrock.EnterpriseJavaDevelopment42.service.interfaces.EmployeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeerServiceImpl implements EmployeerService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public void updateEmployeesStatus(Integer id, /*Enum status,*/ EmployeeStatusDTO employeeStatusDTO) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
    }

    @Override
    public void updateDepartment(String department, Employee employee) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employee.getId());
       employeeRepository.save(employee);
    }

   /* public void update(String code, Course course) {
        Optional<Course> optionalCourse = courseRepository.findById(code);
        if(optionalCourse.isPresent()) {
            // SI existe el curso dentro de nuestra BD
            // Sobreescribimos la clave primaria del modelo por el ID que le pasamos por parámetro
            course.setCode(code);
            // Actualizamos el curso
            courseRepository.save(course);
        } else {
            // Si no existe lanzar un error
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found");
        }

    */
/*
    @Override
    public void updateName(String code, CourseNameDTO courseNameDTO) {
        // Verificar si el curso existe
        Optional<Course> optionalCourse = courseRepository.findById(code);
        if(!optionalCourse.isPresent()) {
            // Si no existe lanzar un 404
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found :(");
        }

        // Actualizamos el nombre
        optionalCourse.get().setName(courseNameDTO.getName());

        // Actualizando el curso en la base de datos
        courseRepository.save(optionalCourse.get());
    }*/

//    @Override
//    public void delete(String code) {
//        if(!courseRepository.findById(code).isPresent()) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found");
//        }
//
//        courseRepository.deleteById(code);
//
//    }


}
