package com.etma.gateway.domain.permission.services;

import com.etma.gateway.domain.permission.dto.PermissionDTO;

import java.util.List;

public interface PermissionService {
    List<PermissionDTO> getPermissionByPermissionId(Long permissionId);
    List<PermissionDTO> getPermissionInRoleId(List<Long> roleId);
    void deletePermissionByPermissionId(Long permissionId);
}
