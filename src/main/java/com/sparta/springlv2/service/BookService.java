package com.sparta.springlv2.service;

import com.sparta.springlv2.dto.BookRequestDto;
import com.sparta.springlv2.dto.BookResponseDto;
import com.sparta.springlv2.entity.Book;
import com.sparta.springlv2.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookResponseDto createBook(BookRequestDto requestDto) {
        // RequestDto -> Entity
        Book book = new Book(requestDto);
        // DB 저장
        Book saveBook = bookRepository.save(book);

        return new BookResponseDto(saveBook);
    }

    public List<BookResponseDto> getBookList() {
        return bookRepository.findAllByOrderByPubDateAsc().stream().map(BookResponseDto::new).toList();
    }
}
