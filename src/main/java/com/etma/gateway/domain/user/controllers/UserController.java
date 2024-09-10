package com.etma.gateway.domain.user.controllers;

import com.etma.gateway.domain.user.dto.InsertUserDTO;
import com.etma.gateway.domain.user.dto.UpdateUserDTO;
import com.etma.gateway.domain.user.requests.UserRequest;
import org.springframework.http.ResponseEntity;

public interface UserController {
    ResponseEntity<?> getAllUsers(UserRequest userRequest);
    ResponseEntity<?> getUserById(Long userId);
    ResponseEntity<?> createNewUser(InsertUserDTO userData);
    ResponseEntity<?> updateUserById(Long userId, UpdateUserDTO userData);
    ResponseEntity<?> deleteUserById(Long userId);
}
