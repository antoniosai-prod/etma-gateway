package com.etma.gateway.domain.scope.controllers.impl;

import com.etma.shared.core.entities.ApiResponseEntity;
import com.etma.shared.core.exceptions.NotFoundException;
import com.etma.gateway.domain.scope.dto.ScopeDTO;
import com.etma.gateway.domain.scope.requests.ScopePaginationRequest;
import com.etma.gateway.domain.scope.services.ScopeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController("v1ScopeController")
@RequestMapping("/v1/user-management/scope")
@Validated
public class V1_ScopeControllerImpl {

    private final ScopeService scopeService;

    @Autowired
    public V1_ScopeControllerImpl(@Qualifier("v1ScopeService") ScopeService scopeService) {
        this.scopeService = scopeService;
    }

    @GetMapping
    public ResponseEntity<?> getAllScopes(@Valid @ModelAttribute ScopePaginationRequest scopeRequest) {

        return new ApiResponseEntity<>(scopeService.getAllScopeByPagination(scopeRequest))
                .setResponseHeaders("scopeRequest", scopeRequest)
                .toResponse(HttpStatus.OK, "Successfully fetched Scope List Data with Pagination");
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getScopeById(@PathVariable Long userId) throws NotFoundException {
        return new ApiResponseEntity<>(scopeService.getDetailScopeByScopeId(userId))
                .setResponseHeaders("userId", userId)
                .toResponse(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createNewScope(@RequestBody @Valid ScopeDTO inputJson) {
        scopeService.createNewScope(inputJson);
        return new ApiResponseEntity<>()
                .setResponseHeaders("inputJson", inputJson)
                .toResponse(HttpStatus.CREATED, "Successfully created new Scope");
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateScopeById(@PathVariable Long userId, @RequestBody @Valid ScopeDTO inputJson) throws NotFoundException {
        scopeService.updateExistingScopeById(userId, inputJson);
        return new ApiResponseEntity<>()
                .setResponseHeaders("inputJson", inputJson)
                .setResponseHeaders("userId", userId)
                .toResponse(HttpStatus.OK, "Update Scope By ID Successfully");
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<?> updateScopeByIdPartially(@PathVariable Long userId, @RequestBody @Valid ScopeDTO inputJson) throws NotFoundException {
        scopeService.updateExistingScopeById(userId, inputJson);
        return new ApiResponseEntity<>()
                .setResponseHeaders("inputJson", inputJson)
                .setResponseHeaders("userId", userId)
                .toResponse(HttpStatus.OK, "Update Scope By Partially Has Been Succeed");
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteScopeById(@PathVariable Long userId) throws NotFoundException {
        scopeService.deleteExistingScopeById(userId);
        return new ApiResponseEntity<>()
                .setResponseHeaders("userId", userId)
                .toResponse(HttpStatus.NO_CONTENT, "Delete Scope By Has Been Succeed");
    }
}
