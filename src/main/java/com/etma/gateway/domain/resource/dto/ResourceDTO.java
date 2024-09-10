package com.etma.gateway.domain.resource.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResourceDTO {
    private Long id;
    private String name;
    private String path;
    private String code;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
