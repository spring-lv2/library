package com.sparta.springlv2.service;

import com.sparta.springlv2.dto.loan.LoanRequestDto;
import com.sparta.springlv2.dto.loan.LoanResponseDto;
import com.sparta.springlv2.entity.Book;
import com.sparta.springlv2.entity.Loan;
import com.sparta.springlv2.entity.User;
import com.sparta.springlv2.exception.CustomBadRequestException;
import com.sparta.springlv2.repository.BookRepository;
import com.sparta.springlv2.repository.LoanRepository;
import com.sparta.springlv2.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public LoanService(LoanRepository loanRepository, BookRepository bookRepository, UserRepository userRepository) {
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public ResponseEntity loanBook(LoanRequestDto loanRequestDto) {
        // RequestDto -> Entity
        Loan loan = new Loan(loanRequestDto);

        Long userId = loan.getUserId();
        Long bookId = loan.getBookId();

        // 대여 확인
        Optional<Loan> loanStatus = loanRepository.findByBookIdAndLoanStatus(bookId, false);
        if (loanStatus.isPresent()) { // 5번 추가 구현
            throw new CustomBadRequestException("이미 대여된 책입니다.");
        }

        // 패널티 날짜 확인
        User user = userRepository.findByIdAndPenalty(userId, true);
        System.out.println("user = " + user);
        if (user != null) {
            if (user.getPenaltyDate().isAfter(LocalDateTime.now())) { // 현재 시간과 패널티 시간 비교
                throw new CustomBadRequestException("패널티 상태입니다. 2주 동안 대여하실 수 없습니다.");
            } else {
                // 패널티 해제
                user.releasePenalty();
            }
        }
        // DB 저장
        loanRepository.save(loan);

        return ResponseEntity.status(HttpStatus.OK).body("대여에 성공했습니다.");
    }

    @Transactional
    public ResponseEntity returnBook(Long id) {
        // 반납 확인
        Optional<Loan> returnLoan = loanRepository.findByIdAndLoanStatus(id, true);

        if (returnLoan.isPresent()) { // 값이 없다면
            System.out.println("returnLoan = " + returnLoan);
            throw new CustomBadRequestException("이미 반납된 책입니다.");
        }

        // 대여 확인
        Loan findLoan = loanRepository.findById(id)
                .orElseThrow(() -> new CustomBadRequestException("대여되지 않은 책입니다."));

        // 반납 패널티 확인
        LocalDateTime loanDate = findLoan.getLoanDate();
        LocalDateTime now = LocalDateTime.now();

        Duration duration = Duration.between(loanDate, now); // 날짜간 차이

        if (duration.toDays() > 7) {
            Long userId = findLoan.getUserId();
            // 유저 정보 가져오기
            User user = userRepository.findById(userId).orElseThrow(() -> new CustomBadRequestException("유저가 존재하지 않습니다."));
            user.penalty(); // 패널티 부여 (2주)
        }

        findLoan.update(true); // 반납 완료

        return ResponseEntity.status(HttpStatus.OK).body("반납에 성공했습니다.");
    }

    public List<LoanResponseDto> getLoanBookList() {
        List<Loan> loans = loanRepository.findByLoanStatusOrderByLoanDateAsc(false); // 추가 구현 : 반납 완료 제외
        List<LoanResponseDto> loanResponseDtos = new ArrayList<>();

        for (Loan loan : loans) {
            Long userId = loan.getUserId();
            Long bookId = loan.getBookId();

            // 유저 정보 가져오기
            User user = userRepository.findById(userId).orElseThrow(() -> new CustomBadRequestException("유저가 존재하지 않습니다."));
            // 책 제목, 저자 가져오기
            Book book = bookRepository.findById(bookId).orElseThrow(() -> new CustomBadRequestException("책이 존재하지 않습니다."));
            // 유저, 책, 대여 객체로 만들기
            LoanResponseDto loanResponseDto = new LoanResponseDto();

            loanResponseDto.setUserName(user.getUserName());
            loanResponseDto.setPhoneNumber(user.getPhoneNumber());
            loanResponseDto.setBookTitle(book.getTitle());
            loanResponseDto.setBookAuthor(book.getAuthor());
            loanResponseDto.setLoanDate(loan.getLoanDate());

            loanResponseDtos.add(loanResponseDto);
        }

        return loanResponseDtos;
    }
}
