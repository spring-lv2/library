package com.sparta.springlv2.entity;

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
    private Long user_id;
    private Long book_id;
    @Column
    private Boolean loan_status = false;
    @CreationTimestamp
    @Column(name = "loan_date")
    private LocalDateTime loanDate;
    @UpdateTimestamp
    @Column(name = "return_date")
    private LocalDateTime returnDate;

}
