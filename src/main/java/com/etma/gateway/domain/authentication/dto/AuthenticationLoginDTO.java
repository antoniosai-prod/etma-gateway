package com.etma.gateway.domain.authentication.dto;

import lombok.Data;

@Data
public class AuthenticationLoginDTO {
    private String username;
    private String password;
}
