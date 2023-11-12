package com.sparta.springlv2.dto.loan;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class LoanResponseDto {
    private String userName;
    private String phoneNumber;
    private String bookTitle;
    private String bookAuthor;
    private LocalDateTime loanDate;

    public LoanResponseDto() {

    }

    public LoanResponseDto(LoanResponseDto loanResponseDto) {
        this.userName = loanResponseDto.getUserName();
        this.phoneNumber = loanResponseDto.getPhoneNumber();
        this.bookTitle = loanResponseDto.getBookTitle();
        this.bookAuthor = loanResponseDto.getBookAuthor();
        this.loanDate = loanResponseDto.getLoanDate();
    }
}
