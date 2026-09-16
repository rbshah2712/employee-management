package com.employee.Employee.services;

import java.util.List;
import java.util.Optional;
import com.employee.Employee.entity.Department;

public interface DepartmentService {
    List<Department> getAllDepartments();
    
    Optional<Department> getDepartmentById(Long departmentId);
    
    Department createDepartment(Department department);
    
    Optional<Department> updateDepartment(Long departmentId, Department departmentDetails);
    
    boolean deleteDepartment(Long departmentId);

    Optional<Department> patchDepartment(Long id, Department departmentDetails);
}
