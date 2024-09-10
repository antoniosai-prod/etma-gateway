package com.etma.gateway.domain.user.entities;

import com.etma.gateway.domain.role_user.entities.RoleUserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "user_mtd_user", schema = "etma")
public class UserEntity implements Serializable {
    @Id
    @Column(name = "idumr", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usnam", length = 40, nullable = false, unique = true)
    private String username;

    @Column(name = "uspwd", nullable = false)
    private String password;

    @Column(name = "usmal", length = 60, nullable = false, unique = true)
    private String email;

    @Column(name = "usfnm", nullable = false, length = 40)
    private String firstName;

    @Column(name = "uslnm", nullable = false, length = 40)
    private String lastName;

    @Column(name = "usact", nullable = false)
    private Boolean activeStatus;

    @Column(name = "uslas")
    private LocalDateTime Login;

    @Column(name = "zcdat")
    private LocalDateTime createdAt;

    @Column(name = "zudat")
    private LocalDateTime updatedAt;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private List<RoleUserEntity> roleUsers;

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
