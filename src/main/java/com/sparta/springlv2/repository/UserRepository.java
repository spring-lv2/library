package com.sparta.springlv2.repository;

import com.sparta.springlv2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserNumber(String userNumber);

    Optional<User> findByPhoneNumber(String phoneNumber);
}
