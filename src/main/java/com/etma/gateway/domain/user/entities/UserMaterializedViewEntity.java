package com.etma.gateway.domain.user.entities;

import com.etma.gateway.domain.role.entities.RoleEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@Entity
//TODO: Change user_mtd_user to materialized view name
@Table(name = "user_mtd_user", schema = "etma")
public class UserMaterializedViewEntity implements Serializable, UserDetails {
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
    private LocalDateTime lastLogin;

    @Column(name = "zcdat")
    private LocalDateTime createdAt;

    @Column(name = "zudat")
    private LocalDateTime updatedAt;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_cfg_role_user", // Specify the schema and table name
            schema = "etma",
            joinColumns = @JoinColumn(name = "usidx"), // Join column for User entity
            inverseJoinColumns = @JoinColumn(name = "aoidx") // Inverse join column for Role entity
    )
    private List<RoleEntity> roles;

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    public List<String> getRoles() {
        return roles.stream()
                .map(RoleEntity::getName)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
