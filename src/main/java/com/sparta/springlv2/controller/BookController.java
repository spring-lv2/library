package com.sparta.springlv2.controller;


import com.sparta.springlv2.dto.BookRequestDto;
import com.sparta.springlv2.dto.BookResponseDto;
import com.sparta.springlv2.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/")
    public List<BookResponseDto> getBookList() {
        return bookService.getBookList();
    }

    @GetMapping("/{id}")
    public BookResponseDto getBook(@PathVariable Long id) {
        return bookService.getBook(id);
    }

}
