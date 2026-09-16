package com.employee.Employee.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.Employee.entity.Employee;
import com.employee.Employee.dtos.EmployeeRequestDTO;
import com.employee.Employee.entity.Department;
import com.employee.Employee.entity.Roles;
import com.employee.Employee.repository.DepartmentRepository;
import com.employee.Employee.repository.EmployeeRepository;
import com.employee.Employee.repository.RolesRepository;
import com.employee.Employee.services.EmployeeService;
import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @PersistenceContext 
    private EntityManager entityManager;
    
    private final DepartmentRepository departmentRepository;
    private final RolesRepository rolesRepository;
    private final PasswordEncoder passwordEncoder;
    
    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
            DepartmentRepository departmentRepository, RolesRepository rolesRepository, PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.rolesRepository = rolesRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
    
    public Optional<Employee> getEmployeeById(Long employeeId) {
        return employeeRepository.findById(employeeId);
    }
    
    @Transactional 
    public Employee createEmployee(EmployeeRequestDTO requestDTO) {
        Employee employee = new Employee();
        employee.setEmpName(requestDTO.getEmpName());
        employee.setEmpAdd(requestDTO.getEmpAdd());
        employee.setEmpPhone(requestDTO.getEmpPhone());
        employee.setEmpEmail(requestDTO.getEmpEmail());
        employee.setEmpPwd(passwordEncoder.encode(requestDTO.getEmpPwd()));
        employee.setSalary(requestDTO.getSalary());
        Roles role = findRole(requestDTO.getRoleId());
        employee.setRoleId(role);
        employee.setDeptId(findDepartment(requestDTO.getDepartmentId()));
        return employeeRepository.save(employee);
    }
    
    public Optional<Employee> updateEmployee(Long employeeId, EmployeeRequestDTO employeeDetails) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
           // employee.setEmpId(employeeDetails.getEmpId());
            employee.setEmpName(employeeDetails.getEmpName());
            employee.setEmpAdd(employeeDetails.getEmpAdd());
            employee.setEmpPhone(employeeDetails.getEmpPhone());
            employee.setEmpEmail(employeeDetails.getEmpEmail());
            employee.setEmpPwd(employeeDetails.getEmpPwd());
            employee.setSalary(employeeDetails.getSalary());
            Roles role = findRole(employeeDetails.getRoleId());
            employee.setRoleId(role);
            employee.setDeptId(findDepartment(employeeDetails.getDepartmentId()));
            return Optional.of(employeeRepository.save(employee));
        } else {
            return Optional.empty();
        }
    }

    public Optional<Employee> patchEmployee(Long employeeId, Employee employeeDetails) {
    Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
    
    if (optionalEmployee.isPresent()) {
        Employee employee = optionalEmployee.get();
        
        // 1. Only update Name if a new name was provided
        if (employeeDetails.getEmpName() != null) {
            employee.setEmpName(employeeDetails.getEmpName());
        }
        
        // 2. Only update Email if a new email was provided
        if (employeeDetails.getEmpEmail() != null) {
            employee.setEmpEmail(employeeDetails.getEmpEmail());
        }
        
        // 3. Only update Phone if a new phone was provided
        if (employeeDetails.getEmpPhone() != null) {
            employee.setEmpPhone(employeeDetails.getEmpPhone());
        }
        
        // 4. Only update Salary if a new salary was provided
        if (employeeDetails.getSalary() != null) {
            employee.setSalary(employeeDetails.getSalary());
        }

        // 5. Only update Role if a new role was provided
        if (employeeDetails.getRoleId() != null) {
            Roles role = findRole(employeeDetails.getRoleId());
            employee.setRoleId(role);
        }

        if (employeeDetails.getDeptId() != null) {
            employee.setDeptId(findDepartment(employeeDetails.getDeptId()));
        }
        
        // Save and return the updated employee
        return Optional.of(employeeRepository.save(employee));
    }
    
    return Optional.empty();
}

    private Roles findRole(Roles role) {
        if (role == null || role.getRoleId() == null) {
            throw new IllegalArgumentException("roleId is required.");
        }

        return rolesRepository.findById(role.getRoleId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Role with ID " + role.getRoleId() + " does not exist."));
    }

    private Department findDepartment(Department department) {
        if (department == null || department.getDeptId() == null) {
            throw new IllegalArgumentException("departmentId is required.");
        }

        return departmentRepository.findById(department.getDeptId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Department with ID " + department.getDeptId() + " does not exist."));
    }

    private Roles findRole(Long roleId) {
        if (roleId == null) {
            throw new IllegalArgumentException("roleId is required.");
        }

        return rolesRepository.findById(roleId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Role with ID " + roleId + " does not exist."));
    }

    private Department findDepartment(Long departmentId) {
        if (departmentId == null) {
            throw new IllegalArgumentException("departmentId is required.");
        }

        return departmentRepository.findById(departmentId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Department with ID " + departmentId + " does not exist."));
    }

    public boolean deleteEmployee(Long employeeId) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        if (optionalEmployee.isPresent()) {
            employeeRepository.delete(optionalEmployee.get());
            return true;
        } else {
            return false;
        }
    }
    
}
