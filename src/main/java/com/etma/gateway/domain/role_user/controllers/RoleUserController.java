package com.etma.gateway.domain.role_user.controllers;

import org.springframework.http.ResponseEntity;

public interface RoleUserController {

    ResponseEntity<?> attachRole(Long userId, Long roleId);
    ResponseEntity<?> detachRole(Long userId, Long roleId);
    ResponseEntity<?> detachAllRoles(Long userId);
    ResponseEntity<?> getAllRoles(Long userId);
}
