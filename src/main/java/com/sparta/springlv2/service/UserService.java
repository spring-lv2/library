package com.sparta.springlv2.service;

import com.sparta.springlv2.dto.user.UserRequestDto;
import com.sparta.springlv2.dto.user.UserResponseDto;
import com.sparta.springlv2.entity.User;
import com.sparta.springlv2.exception.CustomBadRequestException;
import com.sparta.springlv2.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        // RequestDto -> Entity
        User user = new User(userRequestDto);

        // 핸드폰, 주민번호 존재 확인
        Optional<User> duplicatedUser = userRepository
                .findByUserNumberAndPhoneNumber(user.getUserNumber(), user.getPhoneNumber());
        if (duplicatedUser.isPresent()) {
            throw new CustomBadRequestException("이미 존재하는 회원입니다.");
        }

        // DB 저장
        User saveUser = userRepository.save(user);

        return new UserResponseDto(saveUser);
    }

}
