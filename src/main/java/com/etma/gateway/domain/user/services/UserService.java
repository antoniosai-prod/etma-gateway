package com.etma.gateway.domain.user.services;

import com.etma.shared.core.exceptions.NotFoundException;
import com.etma.shared.core.utils.PaginationUtil;
import com.etma.gateway.domain.user.dto.InsertUserDTO;
import com.etma.gateway.domain.user.dto.UpdateUserDTO;
import com.etma.gateway.domain.user.dto.UserDTO;
import com.etma.gateway.domain.user.requests.UserRequest;

public interface UserService {
    PaginationUtil<UserDTO> getAllUserByPagination(UserRequest userRequest);
    UserDTO getDetailUserByUserId(Long userId) throws NotFoundException;
    void createNewUser(InsertUserDTO userData);
    void updateExistingUserById(Long userId, UpdateUserDTO userData) throws NotFoundException;
    void deleteExistingUserById(Long userId) throws NotFoundException;
}
