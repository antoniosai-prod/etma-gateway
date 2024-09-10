package com.etma.gateway.domain.scope.repositories;

import com.etma.gateway.domain.scope.entities.ScopeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ScopeRepository extends JpaRepository<ScopeEntity, Long>, JpaSpecificationExecutor<ScopeEntity> {
}
