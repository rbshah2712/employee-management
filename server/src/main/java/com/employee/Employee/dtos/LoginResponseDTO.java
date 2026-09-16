package com.employee.Employee.dtos;

import com.employee.Employee.entity.Employee;

public class LoginResponseDTO {
    private String token;
    private Employee employee;

    public LoginResponseDTO(String token, Employee employee) {
        this.token = token;
        this.employee = employee;
    }

    // Getters
    public String getToken() { return token; }
    public Employee getEmployee() { return employee; }
}