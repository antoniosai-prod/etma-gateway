package com.etma.gateway.domain.scope.services;

import com.etma.gateway.domain.scope.dto.ScopeDTO;
import com.etma.gateway.domain.scope.requests.ScopePaginationRequest;
import com.etma.shared.core.exceptions.NotFoundException;
import com.etma.shared.core.utils.PaginationUtil;

public interface ScopeService {
    PaginationUtil<ScopeDTO> getAllScopeByPagination(ScopePaginationRequest scopeRequest);
    ScopeDTO getDetailScopeByScopeId(Long scopeId) throws NotFoundException;
    void createNewScope(ScopeDTO scopeData);
    void updateExistingScopeById(Long scopeId, ScopeDTO scopeData) throws NotFoundException;
    void deleteExistingScopeById(Long scopeId) throws NotFoundException;
}
