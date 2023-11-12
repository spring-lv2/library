package com.sparta.springlv2.controller;

import com.sparta.springlv2.dto.loan.LoanRequestDto;
import com.sparta.springlv2.dto.loan.LoanResponseDto;
import com.sparta.springlv2.service.LoanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loan")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping("/book")
    public ResponseEntity loanBook(@RequestBody LoanRequestDto loanRequestDto) {
        return loanService.loanBook(loanRequestDto);
    }

    @PostMapping("/{id}")
    public ResponseEntity returnBook(@PathVariable Long id) {
        return loanService.returnBook(id);
    }

    @GetMapping("/")
    public List<LoanResponseDto> getLoanBookList() {
        return loanService.getLoanBookList();
    }
}
