package com.etma.gateway.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateUserDTO {

    @Size(max = 40, message = "Username must be less than 255 characters")
    private String username;

    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;

    @Email(message = "Email should be valid")
    @Size(max = 60, message = "Email must be less than 255 characters")
    private String email;

    @Size(max = 40, message = "First name must be less than 40 characters")
    private String firstName;

    @Size(max = 40, message = "Last name must be less than 40 characters")
    private String lastName;

    @NotNull(message = "Active status is mandatory")
    private Boolean activeStatus;
}
