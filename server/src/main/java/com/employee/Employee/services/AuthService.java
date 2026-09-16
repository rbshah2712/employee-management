package com.employee.Employee.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.employee.Employee.config.JwtUtil;
import com.employee.Employee.dtos.LoginRequestDTO;
import com.employee.Employee.dtos.LoginResponseDTO;
import com.employee.Employee.entity.Employee;
import com.employee.Employee.repository.EmployeeRepository;

@Service
public class AuthService {

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(
            EmployeeRepository employeeRepository,
            PasswordEncoder passwordEncoder,
            JwtUtil jwtUtil) {
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public LoginResponseDTO login(LoginRequestDTO loginRequest) {
    Employee employee = employeeRepository
            .findByEmpEmail(loginRequest.getEmail())
            .orElseThrow(() ->
                    new IllegalArgumentException("Invalid email or password"));

    String rawPassword = loginRequest.getPassword();
    String storedPassword = employee.getEmpPwd();

    if (rawPassword == null || rawPassword.isBlank()
            || storedPassword == null || storedPassword.isBlank()) {
        throw new IllegalArgumentException("Invalid email or password");
    }

    boolean validPassword;

    if (storedPassword.startsWith("$2a$")
            || storedPassword.startsWith("$2b$")
            || storedPassword.startsWith("$2y$")) {
        validPassword = passwordEncoder.matches(rawPassword, storedPassword);
    } else {
        validPassword = rawPassword.equals(storedPassword);

        if (validPassword) {
            employee.setEmpPwd(passwordEncoder.encode(rawPassword));
            employeeRepository.save(employee);
        }
    }

    if (!validPassword) {
        throw new IllegalArgumentException("Invalid email or password");
    }

    String token = jwtUtil.generateToken(employee.getEmpEmail(), employee.getEmpId());
    return new LoginResponseDTO(token, employee); 
}
        
    public Employee register(LoginRequestDTO loginRequest) {
        if (employeeRepository.findByEmpEmail(loginRequest.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already in use");
        }

        Employee newEmployee = new Employee();
        newEmployee.setEmpEmail(loginRequest.getEmail());
        newEmployee.setEmpPwd(passwordEncoder.encode(loginRequest.getPassword()));

        return employeeRepository.save(newEmployee);
    }

    public Employee resetPassword(LoginRequestDTO loginRequest) {
        Employee employee = employeeRepository
                .findByEmpEmail(loginRequest.getEmail())
                .orElseThrow(() ->
                        new IllegalArgumentException("Invalid email"));

        employee.setEmpPwd(passwordEncoder.encode(loginRequest.getPassword()));
        return employeeRepository.save(employee);
    }

    public Employee forgotPassword(LoginRequestDTO loginRequest) {
        Employee employee = employeeRepository
                .findByEmpEmail(loginRequest.getEmail())
                .orElseThrow(() ->
                        new IllegalArgumentException("Invalid email"));

        // Here you would typically send a password reset link to the user's email.
        // For simplicity, we are just returning the employee object.
        return employee;
    }
}
