package com.employee.Employee.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.Employee.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByEmpEmail(String empEmail);
}
