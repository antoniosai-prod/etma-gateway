package com.etma.gateway.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class UserDTO implements Serializable {

    private Long id;

    @NotBlank(message = "Username is mandatory")
    @Size(max = 40, message = "Username must be less than 40 characters")
    private String username;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    @Size(max = 60, message = "Email must be less than 40 characters")
    private String email;

    @NotBlank(message = "First name is mandatory")
    @Size(max = 40, message = "First name must be less than 40 characters")
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    @Size(max = 40, message = "Last name must be less than 40 characters")
    private String lastName;

    @NotNull(message = "Active status is mandatory")
    private Boolean activeStatus;

    private LocalDateTime lastLogin;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
