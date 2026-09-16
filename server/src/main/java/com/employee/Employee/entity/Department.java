package com.employee.Employee.entity;
import jakarta.persistence.*;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

import org.springframework.data.annotation.*;
import lombok.*;



@Entity
@Getter
@Setter
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DeptId", nullable = false, updatable = false)
    private Long DeptId;
    @Column(name = "DeptName", nullable = false)
    private String DeptName;
    @Column(name = "DeptDesc", nullable = false)
    private String DeptDesc;
    @CreatedDate
    @Column(name = "CreatedAt", insertable = false, updatable = false)
    private LocalDateTime  CreatedAt;
    @LastModifiedDate 
    @Column(name = "UpdatedAt", insertable = false, updatable = false)
    private LocalDateTime  UpdatedAt;
    
}
