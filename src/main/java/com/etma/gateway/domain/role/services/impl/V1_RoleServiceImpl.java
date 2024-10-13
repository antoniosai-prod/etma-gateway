package com.etma.gateway.domain.role.services.impl;

import com.etma.gateway.domain.role.dto.RoleDTO;
import com.etma.gateway.domain.role.entities.RoleEntity;
import com.etma.gateway.domain.role.repositories.RoleRepository;
import com.etma.gateway.domain.role.requests.RolePaginationRequest;
import com.etma.gateway.domain.role.services.RoleService;
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

@Service("v1RoleService")
public class V1_RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public PaginationUtil<RoleDTO> getAllRoleByPagination(RolePaginationRequest roleRequest) {
        Pageable paging = PageRequest.of(roleRequest.getPage() - 1, roleRequest.getPerPage());

        Specification<RoleEntity> specs = Specification
                .where(null);

        Page<RoleEntity> pagedResult = roleRepository.findAll(specs, paging);

        return new PaginationUtil<>(pagedResult, RoleDTO.class);
    }

    @Override
    public RoleDTO getDetailRoleByRoleId(Long roleId) throws NotFoundException {
        Optional<RoleEntity> userEntityOptional = roleRepository.findById(roleId);

        if(userEntityOptional.isEmpty()) throw new NotFoundException("Role Not Found. Please try with another ID");

        return ObjectMapperUtil.map(userEntityOptional.get(), RoleDTO.class);
    }

    @Override
    @Transactional
    public void createNewRole(RoleDTO roleData) {
        roleRepository.save(ObjectMapperUtil.map(roleData, RoleEntity.class));
    }

    @Override
    @Transactional
    public void updateExistingRoleById(Long roleId, RoleDTO roleData) throws NotFoundException {

        getDetailRoleByRoleId(roleId);

        RoleEntity existingRole = roleRepository.getById(roleId);

        if(roleData.getName() != null) existingRole.setName(roleData.getName());
        if(roleData.getDescription() != null) existingRole.setDescription(roleData.getDescription());

        roleRepository.save(existingRole);
    }

    @Override
    @Transactional
    public void deleteExistingRoleById(Long roleId) throws NotFoundException {

        getDetailRoleByRoleId(roleId);

        roleRepository.deleteById(roleId);
    }
}
