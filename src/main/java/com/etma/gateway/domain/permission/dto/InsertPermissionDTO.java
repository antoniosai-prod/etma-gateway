package com.etma.gateway.domain.permission.dto;

import lombok.Data;

@Data
public class InsertPermissionDTO {
    private Long roleId;
    private Long scopeId;
    private Long resourceId;
}
