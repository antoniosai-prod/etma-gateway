package com.etma.gateway.domain.role_permission.entities;


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
@Table(name = "user_cfg_role_permission", schema = "etma", uniqueConstraints = {@UniqueConstraint(columnNames = {"aridx", "aoidx", "asidx"})})
//@IdClass(RoleUserIdEntity.class)
public class RolePermissionEntity implements Serializable {

    @Id
    @Column(name = "idurp", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "aridx")
    private Long resourceId;

    @Column(name = "aoidx")
    private Long roleId;

    @Column(name = "asidx")
    private Long scopeId;

    @ManyToOne
    @MapsId("resourceId")
    @JoinColumn(name = "aridx")
    private ResourceEntity resource;

    @ManyToOne
    @MapsId("roleId")
    @JoinColumn(name = "aoidx")
    private RoleEntity role;

    @ManyToOne
    @MapsId("scopeId")
    @JoinColumn(name = "asidx")
    private ScopeEntity scope;

    @Column(name = "zcdat")
    private LocalDateTime createdAt;

    @Column(name = "zudat")
    private LocalDateTime updatedAt;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // Getters and Setters
}