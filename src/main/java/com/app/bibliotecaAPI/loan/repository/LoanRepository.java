package com.app.bibliotecaAPI.loan.repository;

import com.app.bibliotecaAPI.loan.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    @Query("SELECT l.book, COUNT(l) AS totalLoans FROM Loan l GROUP BY l.book ORDER BY totalLoans DESC")
    List<Object[]> findMostLoanedBooks();
    @Query("SELECT l.user, COUNT(l) AS totalLoans FROM Loan l GROUP BY l.user ORDER BY totalLoans DESC")
    List<Object[]> findMostUsersLoans();

}
