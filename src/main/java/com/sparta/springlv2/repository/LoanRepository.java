package com.sparta.springlv2.repository;

import com.sparta.springlv2.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {
}
