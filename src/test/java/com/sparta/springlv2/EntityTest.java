package com.sparta.springlv2;

import com.sparta.springlv2.entity.Book;
import com.sparta.springlv2.entity.Loan;
import com.sparta.springlv2.entity.User;
import com.sparta.springlv2.repository.BookRepository;
import com.sparta.springlv2.repository.LoanRepository;
import com.sparta.springlv2.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class EntityTest {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    LoanRepository loanRepository;

    @Test
    @Rollback(value = false)
    @DisplayName("create_at 및 return_at 테스트")
    void test1() {
        // 유저 생성
        User user = new User();
        user.setUsername("TestUser");
        user.setGender('M');
        user.setUser_number("230101");
        user.setPhone_number("010-1234-1234");
        user.setAddress("동네집");

        userRepository.save(user);

        // 책 생성
        Book book = new Book();
        book.setTitle("책1");
        book.setAuthor("작가1");
        book.setPublisher("출판사1");

        bookRepository.save(book);

        // 대여
        Loan loan = new Loan();
        loan.setUser_id(user.getId());
        loan.setBook_id(book.getId());

        loanRepository.save(loan);
    }

    @Test
    @Rollback(value = false)
    @DisplayName("반납 테스트")
    void test2() {
        Loan loan = loanRepository.findById(1L).orElseThrow(NullPointerException::new);
        loan.setLoan_status(true);

        loanRepository.save(loan);
    }
}
