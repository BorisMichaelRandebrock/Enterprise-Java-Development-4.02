package com.randebrock.EnterpriseJavaDevelopment42.model;

import com.randebrock.EnterpriseJavaDevelopment42.enums.EmployeeStatus;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Employee {
    @Id
    @Column(name = "employee_id")
    private Integer id;
    private String department;
    @Column(name = "employee_name")
    private String name;
    @Column(name = "employee_status")
    @Enumerated(EnumType.STRING)
    private EmployeeStatus employeeStatus;
    @OneToMany(mappedBy = "admittedBy")
    private List<Patient> patients;


    public Employee() {
    }

    public Employee(Integer id, String department, String name, EmployeeStatus admitted_by) {
        this.id = id;
        this.department = department;
        this.name = name;
        this.employeeStatus = admitted_by;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EmployeeStatus getAdmitted_by() {
        return employeeStatus;
    }

    public void setAdmitted_by(EmployeeStatus admitted_by) {
        this.employeeStatus = admitted_by;
    }
}
