package com.employee.Employee.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.employee.Employee.dtos.EmployeeRequestDTO;
import com.employee.Employee.entity.Employee;
import com.employee.Employee.services.EmployeeService;
import java.util.*;

@RestController 
@RequestMapping("/api/employees")
public class EmployeeController {
    
  
    EmployeeService employeeService;
    
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
        
    }

    @GetMapping("/getEmployee")
    public ResponseEntity<List<Employee>> getEmployee() {
        List <Employee> employees = employeeService.getAllEmployees();
        if(employees.size() > 0) {
            return ResponseEntity.ok(employees);
        } else {
            return ResponseEntity.noContent().build();
        }
        
    }

    @GetMapping("/getEmployee/{id}")
    public ResponseEntity<Map<String, Object>> getEmployee(@PathVariable Long id) {
        if(id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid employee ID: " + id);
        }

        Optional<Employee> employee = employeeService.getEmployeeById(id);
        if(employee.isEmpty()) {
            throw new IllegalArgumentException("Employee with ID " + id + " does not exist.");
        }

        return ResponseEntity.ok(Map.of(
                "success", true,
                "data", employee.get()));
    }

   @PostMapping("/createEmployee")
    public ResponseEntity<Map<String, Object>> saveEmployee(
        @RequestBody EmployeeRequestDTO employeeDTO) {

        Employee savedEmployee = employeeService.createEmployee(employeeDTO);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
            "success", true,
            "message", "Employee created successfully",
            "data", Map.of(
                "empName", savedEmployee.getEmpName()
        )   
    ));
}

    @PutMapping("/updateEmployee/{id}")
    public ResponseEntity<Map<String, Object>> updateEmployee(
        @PathVariable Long id,
        @RequestBody EmployeeRequestDTO employeeDetails) {

    Employee updatedEmployee = employeeService.updateEmployee(id, employeeDetails)
            .orElseThrow(() ->
                    new IllegalArgumentException(
                            "Employee with ID " + id + " does not exist."));

    return ResponseEntity.ok(Map.of(
            "success", true,
            "message", "Employee updated successfully",
            "data", updatedEmployee
    ));
}


    @PatchMapping("/patchEmployee/{id}")
    public ResponseEntity<Map<String, Object>> patchEmployee(
        @PathVariable Long id,
        @RequestBody Employee employeeDetails) {

    Employee updatedEmployee = employeeService.patchEmployee(id, employeeDetails)
            .orElseThrow(() ->
                    new IllegalArgumentException(
                            "Employee with ID " + id + " does not exist."));

    return ResponseEntity.ok(Map.of(
            "success", true,
            "message", "Employee patched successfully",
            "data", updatedEmployee
    ));
}

    @DeleteMapping("/deleteEmployee/{id}")
    public ResponseEntity<Map<String, Object>> deleteEmployee(
        @PathVariable Long id) {

    boolean deleted = employeeService.deleteEmployee(id);

    if (!deleted) {
        throw new IllegalArgumentException(
                "Employee with ID " + id + " does not exist.");
    }

    return ResponseEntity.ok(Map.of(
            "success", true,
            "message", "Employee deleted successfully"
    ));
}
    
}
