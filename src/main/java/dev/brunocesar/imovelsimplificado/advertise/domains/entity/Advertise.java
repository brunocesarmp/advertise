package dev.brunocesar.imovelsimplificado.advertise.domains.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;

import static java.time.OffsetDateTime.now;
import static java.util.Objects.isNull;

@Data
@Entity
@Table(name = "tb_advertises")
public class Advertise {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;

    @Column(nullable = false)
    private OffsetDateTime createdAt;

    private OffsetDateTime updatedAt;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    @PrePersist
    public void prePersist() {
        if (isNull(createdAt)) {
            createdAt = now();
        }
        updatedAt = createdAt;
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = now();
    }

}
