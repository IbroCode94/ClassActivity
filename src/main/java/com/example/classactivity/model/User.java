package com.example.classactivity.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "user_table")
public class User {
    @Id
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")

    private Long id;
    private String userName;
    @Column(unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
}
