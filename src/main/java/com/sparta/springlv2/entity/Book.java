package com.sparta.springlv2.entity;

import com.sparta.springlv2.dto.BookRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String language;
    private String author;
    private String publisher;
    @CreationTimestamp
    private LocalDateTime pub_date;

    public Book() {

    }

    public Book(BookRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.language = requestDto.getLanguage();
        this.author = requestDto.getAuthor();
        this.publisher = requestDto.getPublisher();
    }
}
