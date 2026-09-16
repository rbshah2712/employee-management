package com.employee.Employee.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.Employee.entity.Department;
import com.employee.Employee.repository.DepartmentRepository;
import com.employee.Employee.services.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    // Implement the methods defined in the DepartmentService interface
    @Autowired
    private DepartmentRepository departmentRepository;
    
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }
    
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }
    
    public Optional<Department> getDepartmentById(Long departmentId) {
        return departmentRepository.findById(departmentId);
    }
    
    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }
    
    public Optional<Department> updateDepartment(Long departmentId, Department departmentDetails) {
        Optional<Department> optionalDepartment = departmentRepository.findById(departmentId);
        if (optionalDepartment.isPresent()) {
            Department department = optionalDepartment.get();
       //     department.setDeptId(departmentDetails.getDeptId());
            department.setDeptName(departmentDetails.getDeptName());
            department.setDeptDesc(departmentDetails.getDeptDesc());
            department.setUpdatedAt(departmentDetails.getUpdatedAt());
            return Optional.of(departmentRepository.save(department));
        } else {
            return Optional.empty();
        }
    }

    public Optional<Department> patchDepartment(Long departmentId, Department departmentDetails) {
        Optional<Department> optionalDepartment = departmentRepository.findById(departmentId);
        if (optionalDepartment.isPresent()) {
            Department department = optionalDepartment.get();
            if (departmentDetails.getDeptName() != null) {
                department.setDeptName(departmentDetails.getDeptName());
            }
            if (departmentDetails.getDeptDesc() != null) {
                department.setDeptDesc(departmentDetails.getDeptDesc());
            }
            department.setUpdatedAt(departmentDetails.getUpdatedAt());
            return Optional.of(departmentRepository.save(department));
        } else {
            return Optional.empty();
        }
    }
    
    public boolean deleteDepartment(Long departmentId) {
        Optional<Department> optionalDepartment = departmentRepository.findById(departmentId);
        if (optionalDepartment.isPresent()) {
            departmentRepository.delete(optionalDepartment.get());
            return true;
        } else {
            return false;
        }
    }
    
}
