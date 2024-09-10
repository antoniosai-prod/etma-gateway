package com.etma.gateway.domain.permission.repositories;

import com.etma.gateway.domain.permission.entities.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionRepository extends JpaRepository<PermissionEntity, Long>, JpaSpecificationExecutor<PermissionEntity> {
    // Find by role ID
    @Query("SELECT p FROM PermissionEntity p WHERE p.role.id IN :roleIds")
    List<PermissionEntity> findByRoleIds(@Param("roleIds") List<Long> roleIds);

    // Find by resource ID
    List<PermissionEntity> findByResourceId(Long resourceId);

    // Find by scope ID
    List<PermissionEntity> findByScopeId(Long scopeId);

    @Query("SELECT p FROM PermissionEntity p WHERE p.id = :id")
    List<PermissionEntity> findAllById(@Param("id") Long permissionId);
}
