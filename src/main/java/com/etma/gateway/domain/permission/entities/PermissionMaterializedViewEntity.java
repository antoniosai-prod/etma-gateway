package com.etma.gateway.domain.permission.entities;

import com.etma.gateway.domain.resource.entities.ResourceEntity;
import com.etma.gateway.domain.role.entities.RoleEntity;
import com.etma.gateway.domain.scope.entities.ScopeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "user_cfg_role_permission", schema = "etma")
public class PermissionMaterializedViewEntity implements Serializable {
    @Id
    @Column(name = "idurp", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "zcdat")
    private LocalDateTime createdAt;

    @Column(name = "zudat")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "aoidx", referencedColumnName = "idumu", nullable = false)
    private RoleEntity role;

    @ManyToOne
    @JoinColumn(name = "aridx", referencedColumnName = "idume", nullable = false)
    private ResourceEntity resource;

    @ManyToOne
    @JoinColumn(name = "asidx", referencedColumnName = "idums", nullable = false)
    private ScopeEntity scope;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

}
