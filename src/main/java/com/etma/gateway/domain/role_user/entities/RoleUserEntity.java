package com.etma.gateway.domain.role_user.entities;

import com.etma.gateway.domain.role.entities.RoleEntity;
import com.etma.gateway.domain.user.entities.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "user_cfg_role_user", schema = "etma")
//@IdClass(RoleUserIdEntity.class)
public class RoleUserEntity implements Serializable {

    @Id
    @Column(name = "iduru", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usidx")
    private Long userId;

    @Column(name = "aoidx")
    private Long roleId;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "usidx")
    private UserEntity user;

    @ManyToOne
    @MapsId("roleId")
    @JoinColumn(name = "aoidx")
    private RoleEntity role;

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