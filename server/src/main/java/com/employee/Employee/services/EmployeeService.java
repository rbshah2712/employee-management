package com.employee.Employee.services;

import java.util.List;
import java.util.Optional;

import com.employee.Employee.dtos.EmployeeRequestDTO;
import com.employee.Employee.entity.Employee;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    
    Optional<Employee> getEmployeeById(Long employeeId);
    
    Employee createEmployee(EmployeeRequestDTO employeeDTO);
    
        Optional<Employee> updateEmployee(Long employeeId, EmployeeRequestDTO employeeDetails);
    
    boolean deleteEmployee(Long employeeId);

    Optional<Employee> patchEmployee(Long id, Employee employeeDetails);
}
