package com.sparta.springlv2.dto.book;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter // TEST
public class BookRequestDto {
    private String title;
    private String author;
    private String language;
    private String publisher;
}
