package com.etma.gateway.domain.permission.controllers.impl;


import com.etma.shared.core.entities.ApiResponseEntity;
import com.etma.shared.core.exceptions.NotFoundException;
import com.etma.gateway.domain.permission.services.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController("v1PermissionController")
@RequestMapping("/v1/user-management/permission")
@Validated
public class V1_PermissionControllerImpl {

    private final PermissionService permissionService;

    @Autowired
    public V1_PermissionControllerImpl(@Qualifier("v1PermissionService") PermissionService permissionService) {
        this.permissionService = permissionService;
    }


    @GetMapping("/{permissionId}")
    public ResponseEntity<?> getUserById(@PathVariable Long permissionId) throws NotFoundException {
        return new ApiResponseEntity<>(permissionService.getPermissionByPermissionId(permissionId))
                .setResponseHeaders("permissionId", permissionId)
                .toResponse(HttpStatus.OK);
    }


    @DeleteMapping("/{permissionId}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long permissionId) throws NotFoundException {
        permissionService.deletePermissionByPermissionId(permissionId);

        return new ApiResponseEntity<>()
                .setResponseHeaders("permissionId", permissionId)
                .toResponse(HttpStatus.NO_CONTENT);
    }


}
