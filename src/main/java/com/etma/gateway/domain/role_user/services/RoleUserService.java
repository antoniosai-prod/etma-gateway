package com.etma.gateway.domain.role_user.services;

public interface RoleUserService {
    void attachUserToRole(Long userId, Long roleId);
    void detachRole(Long userId, Long roleId);
    void detachAllRoles(Long userId);
}
