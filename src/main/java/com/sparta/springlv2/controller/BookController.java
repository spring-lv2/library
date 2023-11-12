package com.sparta.springlv2.controller;


import com.sparta.springlv2.dto.BookRequestDto;
import com.sparta.springlv2.dto.BookResponseDto;
import com.sparta.springlv2.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/booklist")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/")
    public BookResponseDto createBook(@RequestBody BookRequestDto requestDto) {
        return bookService.createBook(requestDto);
    }

}
