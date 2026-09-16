package com.employee.Employee.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.employee.Employee.services.DepartmentService;
import java.util.*;
import com.employee.Employee.entity.Department;

@RestController 
@RequestMapping("/api/departments")
public class DepartmentController {
    
    DepartmentService departmentService;
    
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/getDepartment")
    public ResponseEntity<List<Department>> getDepartment() {
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    @GetMapping("/getDepartment/{id}")
    public ResponseEntity<Optional<Department>> getDepartment(@PathVariable Long id) {
        return ResponseEntity.ok(departmentService.getDepartmentById(id));
    }

    @PostMapping("/createDepartment")
    public ResponseEntity<Department> saveDepartment(@RequestBody Department departmentdetails) {
        return ResponseEntity.ok(departmentService.createDepartment(departmentdetails));
    }
    
    @PutMapping("/updateDepartment/{id}")
    public String updateDepartment(@PathVariable Long id, @RequestBody Department department) {
        if(id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid department ID: " + id);
        } else if(departmentService.getDepartmentById(id).isEmpty()) {
            throw new IllegalArgumentException("Department with ID " + id + " does not exist.");
        } else {
            return departmentService.updateDepartment(id, department)
                    .map(updatedDepartment -> "Department updated successfully")
                    .orElse("Department not found");
        }
    }

    @PatchMapping("/patchDepartment/{id}")
    public String patchDepartment(@PathVariable Long id, @RequestBody Department departmentDetails) {
        if(id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid department ID: " + id);
        } else if(departmentService.getDepartmentById(id).isEmpty()) {
            throw new IllegalArgumentException("Department with ID " + id + " does not exist.");
        } else {
            return departmentService.patchDepartment(id, departmentDetails)
                    .map(updatedDepartment -> "Department patched successfully")
                    .orElse("Department not found");
        }
    }

    @DeleteMapping("/deleteDepartment/{id}")
    public String deleteDepartment(@PathVariable Long id) {
        if(id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid department ID: " + id);
        } else if(departmentService.getDepartmentById(id).isEmpty()) {
            throw new IllegalArgumentException("Department with ID " + id + " does not exist.");
        } else {
            departmentService.deleteDepartment(id);
            return "Department deleted successfully";
        }
    }
}
