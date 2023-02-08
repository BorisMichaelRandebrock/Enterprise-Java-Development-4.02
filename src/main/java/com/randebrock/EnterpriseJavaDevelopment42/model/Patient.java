package com.randebrock.EnterpriseJavaDevelopment42.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
public class Patient {
    @Id
    @Column(name = "patient_id")
    private Integer id;
    @Column(name = "patient_name")
    private String name;
    @Column(name = "date_of_birth")
    private LocalDate birthday;

    @ManyToOne
    @JoinColumn(name = "admitted_by")
    private Employee admittedBy;

    public Patient() {
    }

    public Patient(Integer id, String name, LocalDate birthday, Employee admittedBy) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.admittedBy = admittedBy;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Employee getAdmittedBy() {
        return admittedBy;
    }

    public void setAdmittedBy(Employee admittedBy) {
        this.admittedBy = admittedBy;
    }
}
