package com.employee.Employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.Employee.entity.Roles;


public interface RolesRepository extends JpaRepository<Roles, Long> {
}
