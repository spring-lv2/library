package com.sparta.springlv2.repository;

import com.sparta.springlv2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByIdAndPenalty(Long userId, boolean penalty);

    Optional<User> findByUserNumberAndPhoneNumber(String userNumber, String phoneNumber);
}
