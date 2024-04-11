package com.careerbuilder.careerbuilder.domain.user.entity;

import com.careerbuilder.careerbuilder.domain.user.type.UserRole;
import com.careerbuilder.careerbuilder.domain.user.type.UserStatus;
import com.careerbuilder.careerbuilder.global.common.baseentity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 100, nullable = false)
    @Email
    private String email;

    @Column(length = 100, nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Column(length = 100, nullable = false)
    private String address;

    private LocalDateTime registeredAt;
    private LocalDateTime unRegisteredAt;
    private LocalDateTime lastLoginAt;
}
