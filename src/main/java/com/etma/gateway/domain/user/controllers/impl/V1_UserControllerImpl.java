package com.etma.gateway.domain.user.controllers.impl;

import com.etma.shared.core.entities.ApiResponseEntity;
import com.etma.shared.core.exceptions.NotFoundException;
import com.etma.gateway.domain.user.dto.InsertUserDTO;
import com.etma.gateway.domain.user.dto.UpdateUserDTO;
import com.etma.gateway.domain.user.requests.UserRequest;
import com.etma.gateway.domain.user.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController("v1UserController")
@RequestMapping("/v1/user-management/user")
@Validated
public class V1_UserControllerImpl {

    private final UserService userService;

    @Autowired
    public V1_UserControllerImpl(@Qualifier("v2UserService") UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers(@Valid @ModelAttribute UserRequest userRequest) {
        return new ApiResponseEntity<>(userService.getAllUserByPagination(userRequest))
            .setResponseHeaders("userRequest", userRequest)
            .toResponse(HttpStatus.OK, "Successfully fetched User List Data with Pagination");

    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable Long userId) throws NotFoundException {
        return new ApiResponseEntity<>(userService.getDetailUserByUserId(userId))
            .setResponseHeaders("userId", userId)
            .toResponse(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createNewUser(@RequestBody @Valid InsertUserDTO inputJson) {
        userService.createNewUser(inputJson);
        return new ApiResponseEntity<>()
                .setResponseHeaders("inputJson", inputJson)
                .toResponse(HttpStatus.CREATED, "Successfully created new User");
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUserById(@PathVariable Long userId, @RequestBody @Valid UpdateUserDTO inputJson) throws NotFoundException {
        userService.updateExistingUserById(userId, inputJson);
        return new ApiResponseEntity<>()
                .setResponseHeaders("inputJson", inputJson)
                .setResponseHeaders("userId", userId)
                .toResponse(HttpStatus.OK, "Update User By ID Successfully");
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<?> updateUserByIdPartially(@PathVariable Long userId, @RequestBody @Valid UpdateUserDTO inputJson) throws NotFoundException {
        userService.updateExistingUserById(userId, inputJson);
        return new ApiResponseEntity<>()
                .setResponseHeaders("inputJson", inputJson)
                .setResponseHeaders("userId", userId)
                .toResponse(HttpStatus.OK, "Update User By Partially Has Been Succeed");
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long userId) throws NotFoundException {
        userService.deleteExistingUserById(userId);
        return new ApiResponseEntity<>()
                .setResponseHeaders("userId", userId)
                .toResponse(HttpStatus.NO_CONTENT, "Delete User By Has Been Succeed");
    }
}
