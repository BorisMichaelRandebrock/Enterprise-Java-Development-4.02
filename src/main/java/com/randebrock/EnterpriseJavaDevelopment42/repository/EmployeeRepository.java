package com.randebrock.EnterpriseJavaDevelopment42.repository;

import com.randebrock.EnterpriseJavaDevelopment42.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
