package com.sparta.springlv2.dto;

import com.sparta.springlv2.entity.Book;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BookResponseDto {
    private Long id;
    private String title;
    private String author;
    private String language;
    private String publisher;
    private LocalDateTime pub_date;


    public BookResponseDto(Book book){
        this.id = book.getId();
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.language = book.getLanguage();
        this.publisher = book.getPublisher();
        this.pub_date = book.getPub_date();
    }
}
