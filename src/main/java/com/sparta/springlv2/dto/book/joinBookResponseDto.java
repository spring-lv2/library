package com.sparta.springlv2.dto.book;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class joinBookResponseDto {
    private Long id;
    private String title;
    private String author;
    private String language;
    private String publisher;
    private LocalDateTime pubDate;
    private String loanStatus;

    public joinBookResponseDto(long id, String title, String author, String language, String publisher, LocalDateTime pubDate, String loanStatus) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.language = language;
        this.publisher = publisher;
        this.pubDate = pubDate;
        this.loanStatus = loanStatus;
    }
}
