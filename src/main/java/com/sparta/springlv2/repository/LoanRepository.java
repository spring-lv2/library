package com.sparta.springlv2.repository;

import com.sparta.springlv2.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    Optional<Loan> findByUserIdAndBookIdAndLoanStatus(Long userId, Long bookId, boolean loanStatus);

    Optional<Loan> findByIdAndLoanStatus(Long id, boolean loanStatus);

    List<Loan> findByLoanStatusOrderByLoanDateAsc(boolean loanStatus);
}
