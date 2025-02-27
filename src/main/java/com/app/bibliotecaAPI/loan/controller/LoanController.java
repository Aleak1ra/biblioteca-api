package com.app.bibliotecaAPI.loan.controller;

import com.app.bibliotecaAPI.loan.dto.LoanRequestDTO;
import com.app.bibliotecaAPI.loan.dto.LoanResponseDTO;
import com.app.bibliotecaAPI.loan.service.LoanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping("/createLoan")
    public ResponseEntity<LoanResponseDTO> createLoan(@RequestBody LoanRequestDTO body) {
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

}
