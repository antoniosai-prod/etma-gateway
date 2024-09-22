package com.etma.gateway.domain.permission.services.impl;

import com.etma.gateway.domain.permission.dto.PermissionDTO;
import com.etma.gateway.domain.permission.repositories.PermissionRepository;
import com.etma.gateway.domain.permission.services.PermissionService;
import com.etma.shared.core.utils.ObjectMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("v1PermissionService")
public class V1_PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public List<PermissionDTO> getPermissionByPermissionId(Long permissionId) {
        return ObjectMapperUtil.mapAll(permissionRepository.findAllById(permissionId), PermissionDTO.class);
    }

    @Override
    public List<PermissionDTO> getPermissionInRoleId(List<Long> roleId) {
        return ObjectMapperUtil.mapAll(permissionRepository.findByRoleIds(roleId), PermissionDTO.class);
    }


    @Override
    public void deletePermissionByPermissionId(Long permissionId) {
        permissionRepository.deleteById(permissionId);
    }
}
