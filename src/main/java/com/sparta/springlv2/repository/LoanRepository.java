package com.sparta.springlv2.repository;

import com.sparta.springlv2.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

    Optional<Loan> findByUserIdAndBookIdAndLoanStatus(Long userId, Long bookId, boolean loanStatus);

    Optional<Loan> findByIdAndLoanStatus(Long id, boolean loanStatus);

    List<Loan> findByLoanStatusOrderByLoanDateAsc(boolean loanStatus);

    Optional<Loan> findByBookIdAndLoanStatus(Long bookId, boolean loanStatus);
}
