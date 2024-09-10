package com.etma.gateway.domain.authentication.services.impl;

import com.etma.gateway.domain.authentication.dto.AuthenticationLoginDTO;
import com.etma.gateway.domain.authentication.services.AuthenticationService;
import com.etma.gateway.domain.user.entities.UserMaterializedViewEntity;
import com.etma.gateway.domain.user.repositories.UserMaterializedViewRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserMaterializedViewRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationServiceImpl(
            UserMaterializedViewRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserMaterializedViewEntity authenticate(AuthenticationLoginDTO input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getUsername(),
                        input.getPassword()
                )
        );

        log.info("Input ==> {}", input);

        return userRepository.findByUsername(input.getUsername())
                .orElseThrow();
    }
}