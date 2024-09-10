package com.etma.gateway.domain.role_user.controllers.impl;

import com.etma.shared.core.entities.ApiResponseEntity;
import com.etma.gateway.domain.role_user.controllers.RoleUserController;
import com.etma.gateway.domain.role_user.services.RoleUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/user-management/user/{userId}/role")
@Slf4j
public class V1_RoleUserControllerImpl implements RoleUserController {

    @Autowired
    private RoleUserService roleUserService;

    @Override
    @PutMapping("/{roleId}")
    public ResponseEntity<?> attachRole(@PathVariable Long userId, @PathVariable Long roleId) {
        roleUserService.attachUserToRole(userId, roleId);
        return new ApiResponseEntity<>()
                .setResponseHeaders("userId", userId)
                .setResponseHeaders("roleId", roleId)
                .toResponse(HttpStatus.CREATED, "Successfully attached Role");
    }

    @Override
    @DeleteMapping("/{roleId}")
    public ResponseEntity<?> detachRole(@PathVariable Long userId, @PathVariable Long roleId) {
        roleUserService.detachRole(userId, roleId);
        return new ApiResponseEntity<>()
                .setResponseHeaders("userId", userId)
                .setResponseHeaders("roleId", roleId)
                .toResponse(HttpStatus.OK, "Successfully detached Role");
    }

    @Override
    @DeleteMapping
    public ResponseEntity<?> detachAllRoles(@PathVariable Long userId) {
        roleUserService.detachAllRoles(userId);
        return new ApiResponseEntity<>()
                .setResponseHeaders("userId", userId)
                .toResponse(HttpStatus.OK, "Successfully detached All Roles");
    }

    @Override
    public ResponseEntity<?> getAllRoles(Long userId) {
        return null;
    }
}
