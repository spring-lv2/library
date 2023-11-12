package com.sparta.springlv2.repository;

import com.sparta.springlv2.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
