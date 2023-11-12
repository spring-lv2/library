package com.sparta.springlv2.service;

import com.sparta.springlv2.dto.loan.LoanRequestDto;
import com.sparta.springlv2.entity.Loan;
import com.sparta.springlv2.exception.CustomDuplicatedException;
import com.sparta.springlv2.repository.LoanRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        // 대여 확인
        if (isLoanAlreadyExists(userId, bookId)) {
            throw new CustomDuplicatedException("이미 대여된 책입니다.");
        }

        // DB 저장
        loanRepository.save(loan);

        return ResponseEntity.status(HttpStatus.OK).body("대여에 성공했습니다.");
    }

    @Transactional
    public ResponseEntity returnBook(Long id) {
        // 반납 확인
        Optional<Loan> returnLoan = loanRepository.findByIdAndLoanStatus(id, true);

        if (returnLoan.isPresent()) {
            System.out.println("returnLoan = " + returnLoan);
            throw new CustomDuplicatedException("이미 반납된 책입니다.");
        }

        // 대여 확인
        Loan findLoan = loanRepository.findById(id)
                .orElseThrow(() -> new CustomDuplicatedException("대여되지 않은 책입니다."));
        findLoan.update(true);

        return ResponseEntity.status(HttpStatus.OK).body("반납에 성공했습니다.");
    }

    private boolean isLoanAlreadyExists(Long userId, Long bookId) {
        // 해당 UserId와 BookId로 대출 내역을 조회
        Optional<Loan> existingLoan = loanRepository.findByUserIdAndBookIdAndLoanStatus(userId, bookId, false);

        // 대출 내역이 이미 존재하면 true, 아니면 false 반환
        return existingLoan.isPresent();
    }
}
