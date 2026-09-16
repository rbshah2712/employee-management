package com.employee.Employee.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.Employee.entity.Roles;
import com.employee.Employee.repository.RolesRepository;
import com.employee.Employee.services.RolesService;

@Service
public class RolesServiceImpl implements RolesService {
    @Autowired
    private RolesRepository rolesRepository;
    
    public RolesServiceImpl(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }
    
    public List<Roles> getAllRoles() {
        return rolesRepository.findAll();
    }
    
    public Optional<Roles> getRoleById(Long roleId) {
        return rolesRepository.findById(roleId);
    }
    
    public Roles createRole(Roles roles) {
        return rolesRepository.save(roles);
    }
    
    public Optional<Roles> updateRoles(Long roleId, Roles rolesDetails) {
        Optional<Roles> optionalRoles = rolesRepository.findById(roleId);
        if (optionalRoles.isPresent()) {
            Roles roles = optionalRoles.get();
            roles.setRoleId(rolesDetails.getRoleId());
            roles.setRoleName(rolesDetails.getRoleName());
            roles.setUpdatedAt(rolesDetails.getUpdatedAt());
            return Optional.of(rolesRepository.save(roles));
        } else {
            return Optional.empty();
        }
    }
    
    public boolean deleteRoles(Long roleId) {
        Optional<Roles> optionalRoles = rolesRepository.findById(roleId);
        if (optionalRoles.isPresent()) {
            rolesRepository.delete(optionalRoles.get());
            return true;
        } else {
            return false;
        }
    }
    
}
