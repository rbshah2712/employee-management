package com.employee.Employee.entity;
import jakarta.persistence.*;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.*;
import lombok.*;

@Entity 
@Getter 
@Setter 
public class Employee {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "EmpId", nullable = false, updatable = false)
    private Long empId;
    @Column(name = "EmpName", nullable = false)
    private String empName;
    @Column (name = "EmpAdd", nullable = false)
    private String empAdd;
    @Column (name = "EmpPhone", nullable = false)
    private String empPhone;
    @Column (name = "EmpEmail", nullable = false)
    private String empEmail;
    @JsonIgnore
    @Column (name = "EmpPwd", nullable = false)
    private String empPwd;
    @Column (name = "Salary", nullable = false)
    private Long salary;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "RoleId", referencedColumnName = "RoleId", nullable = false)
    private Roles roleId;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "DeptId", referencedColumnName = "DeptId", nullable = false)
    private Department deptId;
    @CreatedDate
    @Column (name = "CreatedAt", insertable  = false, updatable = false)
    private LocalDateTime  CreatedAt;
    @LastModifiedDate 
    @Column (name = "UpdatedAt", insertable = false, updatable = false)
    private LocalDateTime  UpdatedAt;
}
