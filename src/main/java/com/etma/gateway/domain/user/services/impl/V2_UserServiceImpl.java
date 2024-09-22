package com.etma.gateway.domain.user.services.impl;


import com.etma.shared.core.exceptions.NotFoundException;
import com.etma.shared.core.utils.ObjectMapperUtil;
import com.etma.shared.core.utils.PaginationUtil;
import com.etma.shared.core.utils.StringUtil;
import com.etma.gateway.domain.user.dto.InsertUserDTO;
import com.etma.gateway.domain.user.dto.UpdateUserDTO;
import com.etma.gateway.domain.user.dto.UserDTO;
import com.etma.gateway.domain.user.entities.UserEntity;
import com.etma.gateway.domain.user.entities.UserMaterializedViewEntity;
import com.etma.gateway.domain.user.repositories.UserMaterializedViewRepository;
import com.etma.gateway.domain.user.repositories.UserRepository;
import com.etma.gateway.domain.user.requests.UserRequest;
import com.etma.gateway.domain.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service("v2UserService")
public class V2_UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMaterializedViewRepository userMaterializedViewRepository;

    @Override
    public PaginationUtil<UserDTO> getAllUserByPagination(UserRequest userRequest) {
        Pageable paging = PageRequest.of(userRequest.getPage() - 1, userRequest.getPerPage());

        Specification<UserMaterializedViewEntity> specs = Specification
                .where(null);

        Page<UserMaterializedViewEntity> pagedResult = userMaterializedViewRepository.findAll(specs, paging);

        return new PaginationUtil<>(pagedResult, UserDTO.class);
    }

    @Override
    public UserDTO getDetailUserByUserId(Long userId) throws NotFoundException {
        Optional<UserMaterializedViewEntity> userEntityOptional = userMaterializedViewRepository.findById(userId);

        if(userEntityOptional.isEmpty()) throw new NotFoundException("User Not Found. Please try with another ID");

        return ObjectMapperUtil.map(userEntityOptional.get(), UserDTO.class);
    }

    @Override
    @Transactional
    public void createNewUser(InsertUserDTO userData) {
        userData.setPassword(StringUtil.encryptStringToSHA256(userData.getPassword()));
        userRepository.save(ObjectMapperUtil.map(userData, UserEntity.class));
    }

    @Override
    @Transactional
    public void updateExistingUserById(Long userId, UpdateUserDTO userData) throws NotFoundException {

        getDetailUserByUserId(userId);

        UserEntity existingUser = userRepository.getById(userId);

        if(userData.getUsername() != null) existingUser.setUsername(userData.getUsername());
        if(userData.getPassword() != null) existingUser.setPassword(StringUtil.encryptStringToSHA256(userData.getPassword()));
        if(userData.getEmail() != null) existingUser.setEmail(userData.getEmail());
        if(userData.getFirstName() != null) existingUser.setFirstName(userData.getFirstName());
        if(userData.getLastName() != null) existingUser.setLastName(userData.getLastName());
        if(userData.getActiveStatus() != null) existingUser.setActiveStatus(userData.getActiveStatus());

        userRepository.save(existingUser);
    }

    @Override
    @Transactional
    public void deleteExistingUserById(Long userId) throws NotFoundException {
        getDetailUserByUserId(userId);

        userRepository.deleteById(userId);
    }

//    @Override
//    public Mono<UserMaterializedViewEntity> getUserDetails(String username) {
//        return userMaterializedViewRepository.findByUsernameMono(username);
//    }

//    @Override
//    public Mono<User> findByUsername(String username) {
//        return userRepository.findByUsername(username);
//    }
}
