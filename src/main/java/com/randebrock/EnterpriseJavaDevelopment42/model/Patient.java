package com.randebrock.EnterpriseJavaDevelopment42.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Patient {
    @Id
    @Column(name = "patient_id")
    private Integer id;
    @Column(name = "patient_name")
    private String name;
    @Column(name = "date_of_birth")
    private Date birthday;
   /* @OneToOne
    @JoinColumn(name = "id")*/
    @ManyToOne
    @JoinColumn(name = "admitted_by")
    private Employee admittedBy;

    public Patient() {
    }

    public Patient(Integer id, String name, Date birthday, Employee admittedBy) {
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Employee getAdmittedBy() {
        return admittedBy;
    }

    public void setAdmittedBy(Employee admittedBy) {
        this.admittedBy = admittedBy;
    }
}
