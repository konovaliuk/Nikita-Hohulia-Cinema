package com.hohulia.cinema.JPA.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "user_list")
@AllArgsConstructor
@NoArgsConstructor
public class UserList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Column(name = "email", nullable = false, length = 45)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new LinkedHashSet<>();


}