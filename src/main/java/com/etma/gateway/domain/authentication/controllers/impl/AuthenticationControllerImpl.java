package com.etma.gateway.domain.authentication.controllers.impl;


import com.etma.gateway.core.JwtService;
import com.etma.gateway.domain.authentication.dto.AuthenticationLoginDTO;
import com.etma.gateway.domain.authentication.dto.AuthenticationLoginResponseDTO;
import com.etma.gateway.domain.authentication.services.AuthenticationService;
import com.etma.gateway.domain.user.entities.UserMaterializedViewEntity;
import com.etma.gateway.domain.user.services.UserService;
import com.etma.shared.core.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/v1/authentication")
@RestController
@Slf4j
public class AuthenticationControllerImpl {
    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/test")
    public Object test() throws NotFoundException {
        return userService.getDetailUserByUserId(1L);
//        AuthenticationLoginDTO loginUserDto = new AuthenticationLoginDTO();
//        UserMaterializedViewEntity authenticatedUser = authenticationService.authenticate(loginUserDto);
//
//        log.info("Authentcaiton data => {}", authenticatedUser);
//        return "Hehe";
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationLoginResponseDTO> authenticate(@RequestBody AuthenticationLoginDTO loginUserDto) {
        UserMaterializedViewEntity authenticatedUser = authenticationService.authenticate(loginUserDto);


        String jwtToken = jwtService.generateToken(authenticatedUser);

        AuthenticationLoginResponseDTO loginResponse = new AuthenticationLoginResponseDTO();
        loginResponse.setExpiresIn(jwtService.getExpirationTime());
        loginResponse.setAccessToken(jwtToken);

        return ResponseEntity.ok(loginResponse);
    }
}