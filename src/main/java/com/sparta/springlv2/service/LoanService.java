package com.sparta.springlv2.service;

import com.sparta.springlv2.dto.loan.LoanRequestDto;
import com.sparta.springlv2.entity.Loan;
import com.sparta.springlv2.exception.BookAlreadyLoanedException;
import com.sparta.springlv2.exception.CustomDuplicatedException;
import com.sparta.springlv2.repository.LoanRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoanService {

    private final LoanRepository loanRepository;

    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public ResponseEntity loanBook(LoanRequestDto loanRequestDto) {
        // RequestDto -> Entity
        Loan loan = new Loan(loanRequestDto);

        Long userId = loan.getUserId();
        Long bookId = loan.getBookId();

        // 대출 확인
        if (isLoanAlreadyExists(userId, bookId)) {
            // 이미 대출 중인 경우에 대한 처리를 수행하고, 예를 들어 예외를 던지거나 적절한 응답을 반환할 수 있습니다.
            // 여기서는 간단히 RuntimeException을 던지도록 했습니다.
            throw new CustomDuplicatedException("이미 대여된 책입니다.");
        }

        // DB 저장
        loanRepository.save(loan);

        return ResponseEntity.status(HttpStatus.OK).body("대여에 성공했습니다.");
    }

    private boolean isLoanAlreadyExists(Long userId, Long bookId) {
        // 해당 UserId와 BookId로 대출 내역을 조회
        Optional<Loan> existingLoan = loanRepository.findByUserIdAndBookIdAndLoanStatus(userId, bookId, false);

        // 대출 내역이 이미 존재하면 true, 아니면 false 반환
        return existingLoan.isPresent();
    }
}
