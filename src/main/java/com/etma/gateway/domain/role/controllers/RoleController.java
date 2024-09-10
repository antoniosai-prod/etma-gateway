package com.etma.gateway.domain.role.controllers;

import com.etma.gateway.domain.role.dto.RoleDTO;
import com.etma.gateway.domain.role.requests.RolePaginationRequest;
import org.springframework.http.ResponseEntity;

public interface RoleController {
    ResponseEntity<?> getAllRoles(RolePaginationRequest userRequest);
    ResponseEntity<?> getRoleById(Long userId);
    ResponseEntity<?> createNewRole(RoleDTO userData);
    ResponseEntity<?> updateRoleById(Long userId, RoleDTO userData);
    ResponseEntity<?> deleteRoleById(Long userId);
}
