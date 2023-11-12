package com.sparta.springlv2.entity;

import com.sparta.springlv2.dto.user.UserRequestDto;
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
    @Column(name = "user_name")
    private String userName;
    private char gender;
    @Column(name = "user_number", nullable = false, unique = true)
    private String userNumber;
    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;
    private String address;

    public User () {

    }

    public User(UserRequestDto requestUserDto) {
        this.userName = requestUserDto.getUserName();
        this.gender = requestUserDto.getGender();
        this.userNumber = requestUserDto.getUserNumber();
        this.phoneNumber = requestUserDto.getPhoneNumber();
        this.address = requestUserDto.getAddress();
    }
}