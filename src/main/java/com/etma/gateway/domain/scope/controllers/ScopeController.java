package com.etma.gateway.domain.scope.controllers;

import com.etma.shared.core.exceptions.NotFoundException;
import com.etma.gateway.domain.scope.dto.ScopeDTO;
import com.etma.gateway.domain.scope.requests.ScopePaginationRequest;
import org.springframework.http.ResponseEntity;

public interface ScopeController {
    ResponseEntity<?> getAllScopes(ScopePaginationRequest scopeRequest);
    ResponseEntity<?> getScopeById(Long scopeId) throws NotFoundException;
    ResponseEntity<?> createNewScope(ScopeDTO scopeData);
    ResponseEntity<?> updateScopeById(Long scopeId, ScopeDTO scopeData) throws NotFoundException;
    ResponseEntity<?> updateScopeByIdPartially(Long userId, ScopeDTO inputJson) throws NotFoundException;
    ResponseEntity<?> deleteScopeById(Long scopeId) throws NotFoundException;
}
