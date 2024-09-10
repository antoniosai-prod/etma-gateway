package com.etma.gateway.domain.role.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RoleDTO {

    private Long id;

    @NotBlank(message = "Role name is mandatory")
    @Size(max = 40, message = "Role name must not exceed 40 characters")
    private String name;

    @NotBlank(message = "Description is mandatory")
    @Size(max = 255, message = "Description must not exceed 40 characters")
    private String description;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}