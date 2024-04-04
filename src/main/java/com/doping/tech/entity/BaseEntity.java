package com.doping.tech.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean status;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    @PrePersist
    void persist() {
        this.status = true;
        this.createdDate = LocalDateTime.now();
    }

    @PreUpdate
    void update() {
        this.updatedDate = LocalDateTime.now();
    }
}
