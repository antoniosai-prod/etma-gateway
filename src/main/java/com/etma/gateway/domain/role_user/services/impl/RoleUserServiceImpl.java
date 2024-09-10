package com.etma.gateway.domain.role_user.services.impl;

import com.etma.gateway.domain.role_user.entities.RoleUserEntity;
import com.etma.gateway.domain.role_user.repositories.RoleUserRepository;
import com.etma.gateway.domain.role_user.services.RoleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleUserServiceImpl implements RoleUserService {

    @Autowired
    private RoleUserRepository roleUserRepository;

    @Override
    @Transactional
    public void attachUserToRole(Long userId, Long roleId) {
        RoleUserEntity roleUserEntity = new RoleUserEntity();
        roleUserEntity.setRoleId(roleId);
        roleUserEntity.setUserId(userId);

        roleUserRepository.save(roleUserEntity);
    }

    @Override
    @Transactional
    public void detachRole(Long userId, Long roleId) {
        roleUserRepository.detachUserRole(userId, roleId);
    }

    @Override
    @Transactional
    public void detachAllRoles(Long userId) {
        roleUserRepository.detachAllUserRole(userId);
    }
}
