package com.employee.Employee.entity;
import jakarta.persistence.*;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

import org.springframework.data.annotation.*;
import lombok.*;

@Entity
@Getter
@Setter
public class Roles {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "RoleId", nullable = false, updatable = false)
    private Long  RoleId;
    @Column(name = "RoleName", nullable = false)
    private String RoleName;
    @CreatedDate 
    @Column(name = "CreatedAt", insertable  = false, updatable = false)
    private LocalDateTime  CreatedAt;
    @LastModifiedDate 
    @Column(name = "UpdatedAt", insertable = false, updatable = false)
    private LocalDateTime  UpdatedAt;

    
}
