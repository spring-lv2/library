package com.sparta.springlv2;

import com.sparta.springlv2.controller.BookController;
import com.sparta.springlv2.dto.book.BookRequestDto;
import com.sparta.springlv2.dto.book.BookResponseDto;
import com.sparta.springlv2.entity.Book;
import com.sparta.springlv2.repository.BookRepository;
import com.sparta.springlv2.repository.LoanRepository;
import com.sparta.springlv2.service.BookService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@WebMvcTest(BookController.class) // 컨트롤러를 테스트 하기 위해서 HTTP 호출 제공
@MockBean(JpaMetamodelMappingContext.class)
public class BookTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @MockBean
    BookRepository bookRepository;
    @MockBean
    LoanRepository loanRepository;

    @Test
    @DisplayName("POST 책 등록 로직 확인")
    void bookCreateTest() throws Exception{
        // Given : 주어진 상황, 상태를 설정하는 단계
        BookRequestDto requestDto = new BookRequestDto();
        requestDto.setTitle("테스트");
        requestDto.setLanguage("JAVA");
        requestDto.setPublisher("출판사1");
        requestDto.setAuthor("저자1");

        Book book = new Book(requestDto);
        book.setId(0L);
        Mockito.when(bookRepository.save(book)).thenReturn(book);


        // When : 만일, 테스트하려는 행동이나 작업
        // save 메서드가 호출될 때 빈 Book 객체를 리턴하도록 설정
        BookService bookService = new BookService(bookRepository, loanRepository);
        BookResponseDto responseDto = bookService.createBook(requestDto);

        // Then : 그러면, 기대하는 결과를 나타내는 단계
        assertEquals("테스트", responseDto.getTitle());
        assertEquals("저자1", responseDto.getAuthor());
        assertEquals("JAVA", responseDto.getLanguage());
        assertEquals("출판사1", responseDto.getPublisher());
        assertEquals(LocalDateTime.now(), responseDto.getPubDate());
        assertEquals(null, responseDto.getId());
    }
}
