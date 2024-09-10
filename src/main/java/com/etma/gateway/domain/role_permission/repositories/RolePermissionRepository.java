package com.etma.gateway.domain.role_permission.repositories;

import com.etma.gateway.domain.role_permission.entities.RolePermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolePermissionRepository extends JpaRepository<RolePermissionEntity, Long> {
}
