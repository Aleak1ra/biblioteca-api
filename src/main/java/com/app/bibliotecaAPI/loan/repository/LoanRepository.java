package com.app.bibliotecaAPI.loan.repository;

import com.app.bibliotecaAPI.loan.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {
}
