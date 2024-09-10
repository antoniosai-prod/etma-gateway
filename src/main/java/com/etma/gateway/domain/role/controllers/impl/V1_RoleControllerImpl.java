package com.etma.gateway.domain.role.controllers.impl;


import com.etma.shared.core.entities.ApiResponseEntity;
import com.etma.shared.core.exceptions.NotFoundException;
import com.etma.gateway.domain.role.dto.RoleDTO;
import com.etma.gateway.domain.role.requests.RolePaginationRequest;
import com.etma.gateway.domain.role.services.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController("v1RoleController")
@RequestMapping("/v1/user-management/role")
@Validated
public class V1_RoleControllerImpl {

    private final RoleService roleService;

    @Autowired
    public V1_RoleControllerImpl(@Qualifier("v1RoleService") RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<?> getAllRoles(@Valid @ModelAttribute RolePaginationRequest roleRequest) {
        return new ApiResponseEntity<>(roleService.getAllRoleByPagination(roleRequest))
                .setResponseHeaders("roleRequest", roleRequest)
                .toResponse(HttpStatus.OK, "Successfully fetched Role List Data with Pagination");

    }

    @GetMapping("/{roleId}")
    public ResponseEntity<?> getRoleById(@PathVariable Long roleId) throws NotFoundException {
        return new ApiResponseEntity<>(roleService.getDetailRoleByRoleId(roleId))
                .setResponseHeaders("roleId", roleId)
                .toResponse(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createNewRole(@RequestBody @Valid RoleDTO inputJson) {
        roleService.createNewRole(inputJson);
        return new ApiResponseEntity<>()
                .setResponseHeaders("inputJson", inputJson)
                .toResponse(HttpStatus.CREATED, "Successfully created new Role");
    }

    @PutMapping("/{roleId}")
    public ResponseEntity<?> updateRoleById(@PathVariable Long roleId, @RequestBody @Valid RoleDTO inputJson) throws NotFoundException {
        roleService.updateExistingRoleById(roleId, inputJson);
        return new ApiResponseEntity<>()
                .setResponseHeaders("inputJson", inputJson)
                .setResponseHeaders("roleId", roleId)
                .toResponse(HttpStatus.OK, "Update Role By ID Successfully");
    }

    @PatchMapping("/{roleId}")
    public ResponseEntity<?> updateRoleByIdPartially(@PathVariable Long roleId, @RequestBody @Valid RoleDTO inputJson) throws NotFoundException {
        roleService.updateExistingRoleById(roleId, inputJson);
        return new ApiResponseEntity<>()
                .setResponseHeaders("inputJson", inputJson)
                .setResponseHeaders("roleId", roleId)
                .toResponse(HttpStatus.OK, "Update Role By Partially Has Been Succeed");
    }

    @DeleteMapping("/{roleId}")
    public ResponseEntity<?> deleteRoleById(@PathVariable Long roleId) throws NotFoundException {
        roleService.deleteExistingRoleById(roleId);
        return new ApiResponseEntity<>()
                .setResponseHeaders("roleId", roleId)
                .toResponse(HttpStatus.NO_CONTENT, "Delete Role By Has Been Succeed");
    }
}
