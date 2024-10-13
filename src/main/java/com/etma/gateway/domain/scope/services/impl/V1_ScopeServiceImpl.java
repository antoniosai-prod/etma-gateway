package com.etma.gateway.domain.scope.services.impl;

import com.etma.gateway.domain.scope.dto.ScopeDTO;
import com.etma.gateway.domain.scope.entities.ScopeEntity;
import com.etma.gateway.domain.scope.repositories.ScopeRepository;
import com.etma.gateway.domain.scope.requests.ScopePaginationRequest;
import com.etma.gateway.domain.scope.services.ScopeService;
import com.etma.shared.core.exceptions.NotFoundException;
import com.etma.shared.core.utils.ObjectMapperUtil;
import com.etma.shared.core.utils.PaginationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service("v1ScopeService")
public class V1_ScopeServiceImpl implements ScopeService {

    @Autowired
    private ScopeRepository scopeRepository;

    @Override
    public PaginationUtil<ScopeDTO> getAllScopeByPagination(ScopePaginationRequest scopeRequest) {
        Pageable paging = PageRequest.of(scopeRequest.getPage() - 1, scopeRequest.getPerPage());

        Specification<ScopeEntity> specs = Specification
                .where(null);

        Page<ScopeEntity> pagedResult = scopeRepository.findAll(specs, paging);

        return new PaginationUtil<>(pagedResult, ScopeDTO.class);
    }

    @Override
    public ScopeDTO getDetailScopeByScopeId(Long scopeId) throws NotFoundException {
        Optional<ScopeEntity> scopeEntityOptional = scopeRepository.findById(scopeId);

        if(scopeEntityOptional.isEmpty()) throw new NotFoundException("Scope Not Found. Please try with another ID");

        return ObjectMapperUtil.map(scopeEntityOptional.get(), ScopeDTO.class);
    }

    @Override
    @Transactional
    public void createNewScope(ScopeDTO scopeData) {
        scopeRepository.save(ObjectMapperUtil.map(scopeData, ScopeEntity.class));
    }

    @Override
    @Transactional
    public void updateExistingScopeById(Long scopeId, ScopeDTO scopeData) throws NotFoundException {

        getDetailScopeByScopeId(scopeId);

        ScopeEntity existingScope = scopeRepository.getById(scopeId);

        if(scopeData.getName() != null) existingScope.setName(scopeData.getName());
        if(scopeData.getDescription() != null) existingScope.setDescription(scopeData.getDescription());

        scopeRepository.save(existingScope);
    }

    @Override
    @Transactional
    public void deleteExistingScopeById(Long scopeId) throws NotFoundException {

        getDetailScopeByScopeId(scopeId);

        scopeRepository.deleteById(scopeId);
    }
}
