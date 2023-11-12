package com.sparta.springlv2.service;

import com.sparta.springlv2.dto.book.BookRequestDto;
import com.sparta.springlv2.dto.book.BookResponseDto;
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

    public BookResponseDto getBook(Long id) {
        return new BookResponseDto(findBook(id));
    }

    private Book findBook(Long id) {
        return bookRepository.findById(id).orElseThrow();
    }
}
