package com.sparta.springlv2.dto.user;

import com.sparta.springlv2.entity.User;
import lombok.Getter;

@Getter
public class UserResponseDto {
    private Long id;
    private String userName;
    private char gender;
    private String phoneNumber;
    private String address;

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.userName = user.getUserName();
        this.gender = user.getGender();
        this.phoneNumber = user.getPhoneNumber();
        this.address = user.getAddress();
    }
}
