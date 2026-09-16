package com.employee.Employee.controllers;

import org.springframework.web.bind.annotation.*;
import com.employee.Employee.entity.Roles;
import com.employee.Employee.services.RolesService;
import java.util.*;

@RestController 
@RequestMapping("/api/roles")
public class RolesController {
    
    RolesService rolesService;
    
    public RolesController(RolesService rolesService) {
        this.rolesService = rolesService;
    }

    @GetMapping("/getRoles")
    public List<Roles> getRoles() {
        return rolesService.getAllRoles();
    }

    @GetMapping("/getRoles/{id}")
    public Optional<Roles> getRoles(@PathVariable Long id) {
        return rolesService.getRoleById(id);
    }

    @PostMapping("/createRoles")
    public Roles saveRoles(@RequestBody Roles rolesdetails) {
        return rolesService.createRole(rolesdetails);
    }

    @PutMapping ("/updateRoles/{id}")
    public String updateRoles(@PathVariable Long id, @RequestBody Roles roles) {    
        
        if(id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid role ID: " + id);
        } else if(rolesService.getRoleById(id).isEmpty()) {
            throw new IllegalArgumentException("Role with ID " + id + " does not exist.");
        } else {
            return rolesService.updateRoles(id, roles)
                    .map(updatedRoles -> "Role updated successfully")
                    .orElse("Role not found");
        }
    }
    
    @PatchMapping ("/patchRoles/{id}")
    public String patchRoles(@PathVariable Long id, @RequestBody Roles rolesDetails) {  
        
        if(id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid role ID: " + id);
        } else if(rolesService.getRoleById(id).isEmpty()) {
            throw new IllegalArgumentException("Role with ID " + id + " does not exist.");
        } else {
            return rolesService.updateRoles(id, rolesDetails)
                    .map(updatedRoles -> "Role patched successfully")
                    .orElse("Role not found");
        }
    }

    @DeleteMapping ("/deleteRoles/{id}")
    public String deleteRoles(@PathVariable Long id) {  
        if(id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid role ID: " + id);
        } else if(rolesService.getRoleById(id).isEmpty()) {
            throw new IllegalArgumentException("Role with ID " + id + " does not exist.");
        } else {
            boolean deleted = rolesService.deleteRoles(id);
            return deleted ? "Role deleted successfully" : "Role not found";
        }
    }
}
