package com.employee.Employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.Employee.entity.Department;


public interface DepartmentRepository extends JpaRepository<Department, Long> {
}