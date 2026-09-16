package com.employee.Employee.services;

import java.util.List;
import java.util.Optional;
import com.employee.Employee.entity.Roles;

public interface RolesService {
    List<Roles> getAllRoles();
    
    Optional<Roles> getRoleById(Long roleId);
    
    Roles createRole(Roles role);
    
    Optional<Roles> updateRoles(Long roleId, Roles roleDetails);
    
    boolean deleteRoles(Long roleId);
}
