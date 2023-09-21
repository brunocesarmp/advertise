package dev.brunocesar.imovelsimplificado.advertise.domains;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;

import static java.time.OffsetDateTime.now;
import static java.util.Objects.isNull;

@Data
@Entity
@Table(name = "advertises")
public class Advertise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private OffsetDateTime createdAt;

    private OffsetDateTime updatedAt;

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
