package com.etma.gateway.domain.role.services;

import com.etma.shared.core.exceptions.NotFoundException;
import com.etma.shared.core.utils.PaginationUtil;
import com.etma.gateway.domain.role.dto.RoleDTO;
import com.etma.gateway.domain.role.requests.RolePaginationRequest;

public interface RoleService {
    PaginationUtil<RoleDTO> getAllRoleByPagination(RolePaginationRequest roleRequest);
    RoleDTO getDetailRoleByRoleId(Long roleId) throws NotFoundException;
    void createNewRole(RoleDTO roleData);
    void updateExistingRoleById(Long roleId, RoleDTO roleData) throws NotFoundException;
    void deleteExistingRoleById(Long roleId) throws NotFoundException;
}
