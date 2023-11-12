package com.sparta.springlv2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private char gender;
    @Column(nullable = false, unique = true)
    private String user_number;
    @Column(nullable = false, unique = true)
    private String phone_number;
    private String address;
}