package com.etma.gateway.domain.role_user.repositories;

import com.etma.gateway.domain.role_user.entities.RoleUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface RoleUserRepository extends JpaRepository<RoleUserEntity, Long> {

    @Modifying
    @Transactional
    @Query("DELETE FROM RoleUserEntity ru WHERE ru.userId = :userId AND ru.roleId = :roleId")
    void detachUserRole(@Param("userId") Long userId, @Param("roleId") Long roleId);


    @Modifying
    @Transactional
    @Query("DELETE FROM RoleUserEntity ru WHERE ru.userId = :userId")
    void detachAllUserRole(@Param("userId") Long userId);
}
