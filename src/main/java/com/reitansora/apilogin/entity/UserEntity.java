package com.reitansora.apilogin.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "users")
@Getter
@Setter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String user_id;
    @Column
    private String user_nickname;
    @Column
    private String user_email;
    @Column
    private String user_password;
    @Column(updatable = false, nullable = false)
    private Instant created_at;

    @PrePersist
    protected void onCreate() {
        created_at = Instant.now();
    }

}
