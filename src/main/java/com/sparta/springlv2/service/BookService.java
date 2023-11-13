package com.sparta.springlv2.service;

import com.sparta.springlv2.dto.book.BookRequestDto;
import com.sparta.springlv2.dto.book.BookResponseDto;
import com.sparta.springlv2.entity.Book;
import com.sparta.springlv2.repository.BookRepository;
import com.sparta.springlv2.repository.LoanRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final LoanRepository loanRepository;

    public BookService(BookRepository bookRepository, LoanRepository loanRepository) {
        this.bookRepository = bookRepository;
        this.loanRepository = loanRepository;
    }

    public BookResponseDto createBook(BookRequestDto requestDto) {
        // RequestDto -> Entity
        Book book = new Book(requestDto);
        // DB 저장
        Book saveBook = bookRepository.save(book);

        return new BookResponseDto(saveBook);
    }

    public List<BookResponseDto> getBookList() {
        // TODO: 도서 대출 가능 여부 기능 선택 또는 조회시 책을 빌릴 수 있는지 나타내기.
        return bookRepository.findAllByOrderByPubDateAsc().stream().map(BookResponseDto::new).toList();
    }

    public BookResponseDto getBook(Long id) {
        return new BookResponseDto(findBook(id));
    }

    private Book findBook(Long id) {
        return bookRepository.findById(id).orElseThrow();
    }
}
