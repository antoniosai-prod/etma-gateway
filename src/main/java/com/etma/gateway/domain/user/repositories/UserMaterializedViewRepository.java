package com.etma.gateway.domain.user.repositories;

import com.etma.gateway.domain.user.entities.UserMaterializedViewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserMaterializedViewRepository extends JpaRepository<UserMaterializedViewEntity, Long>, JpaSpecificationExecutor<UserMaterializedViewEntity> {
    Optional<UserMaterializedViewEntity> findByEmail(String email);
    Optional<UserMaterializedViewEntity> findByUsername(String username);
//    Mono<UserMaterializedViewEntity> findByUsernameMono(String username);

}
