package com.etma.gateway.domain.permission.dto;

import com.etma.gateway.domain.resource.dto.ResourceDTO;
import com.etma.gateway.domain.role.dto.RoleDTO;
import com.etma.gateway.domain.scope.dto.ScopeDTO;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PermissionDTO {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private RoleDTO role;
    private ResourceDTO resource;
    private ScopeDTO scope;
}
