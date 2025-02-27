package com.app.bibliotecaAPI.loan.controller;

import com.app.bibliotecaAPI.loan.dto.BookLoanStatsDTO;
import com.app.bibliotecaAPI.loan.dto.LoanRequestDTO;
import com.app.bibliotecaAPI.loan.dto.LoanResponseDTO;
import com.app.bibliotecaAPI.loan.dto.UserLoanStatsDTO;
import com.app.bibliotecaAPI.loan.service.LoanService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loans")
@Validated
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping("/createLoan")
    public ResponseEntity<LoanResponseDTO> createLoan(@Valid @RequestBody LoanRequestDTO body) {
        return ResponseEntity.ok(loanService.createLoan(body));
    }

    @PutMapping("/updateLoan/{id}")
    public ResponseEntity<LoanResponseDTO> updateLoan(@PathVariable Long id) {
        return ResponseEntity.ok(loanService.updateLoan(id));
    }

    @GetMapping("/getAllLoans")
    public ResponseEntity<List<LoanResponseDTO>> getAllLoads() {
        return ResponseEntity.ok(loanService.getAllLoans());
    }

    @GetMapping("/reports/mostLoanedBooks")
    public List<BookLoanStatsDTO> getMostLoanedBooks() {
        return loanService.getMostLoanedBooks();
    }

    @GetMapping("/reports/mostUsersLoans")
    public List<UserLoanStatsDTO> getMostUsersLoans() {
        return loanService.getMostUsersLoans();
    }
}
