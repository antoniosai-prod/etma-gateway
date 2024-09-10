package com.etma.gateway.domain.resource.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_mtd_resource", schema = "etma")
public class ResourceEntity implements Serializable {
    @Id
    @Column(name = "idume", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "aresc", length = 40, nullable = false)
    private String name;

    @Column(name = "arpat", length = 40, nullable = false)
    private String path;

    @Column(name = "arcod", length = 40, nullable = false, unique = true)
    private String code;

    @Column(name = "ardes", nullable = false, unique = true)
    private String description;

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

}
