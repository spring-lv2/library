package com.sparta.springlv2.entity;

import com.sparta.springlv2.dto.loan.LoanRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@Table(name = "loan")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "book_id")
    private Long bookId;
    @Column(name = "loan_status")
    private Boolean loanStatus = false;
    @CreationTimestamp
    @Column(name = "loan_date")
    private LocalDateTime loanDate;
    @UpdateTimestamp
    @Column(name = "return_date")
    private LocalDateTime returnDate;

    public Loan() {

    }

    public Loan(LoanRequestDto loanRequestDto) {
        this.userId = loanRequestDto.getUserId();
        this.bookId = loanRequestDto.getBookId();
    }

    public void update(boolean loanStatus) {
        this.loanStatus = loanStatus;
    }
}
