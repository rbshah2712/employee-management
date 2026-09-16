package com.employee.Employee.dtos;
import lombok.Data;

@Data
public class EmployeeRequestDTO {
    private String empName;
    private String empAdd;
    private String empPhone;
    private String empEmail;
    private String empPwd;
    private Long salary;
    private Long roleId;       // Received as an ID from the client
    private Long departmentId; // Received as an ID from the client
}
