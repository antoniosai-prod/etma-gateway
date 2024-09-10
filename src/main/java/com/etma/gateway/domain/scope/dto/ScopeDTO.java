package com.etma.gateway.domain.scope.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ScopeDTO {

    private Long id;

    @NotBlank(message = "Name is mandatory")
    @Size(max = 40, message = "Name cannot exceed 40 characters")
    private String name;

    @NotBlank(message = "Description is mandatory")
    private String description;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
