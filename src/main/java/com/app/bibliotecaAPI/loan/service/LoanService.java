package com.app.bibliotecaAPI.loan.service;

import com.app.bibliotecaAPI.book.dto.BookResponseDTO;
import com.app.bibliotecaAPI.book.model.Book;
import com.app.bibliotecaAPI.book.model.BookStatus;
import com.app.bibliotecaAPI.book.repository.BookRepository;
import com.app.bibliotecaAPI.loan.dto.BookLoanStatsDTO;
import com.app.bibliotecaAPI.loan.dto.LoanRequestDTO;
import com.app.bibliotecaAPI.loan.dto.LoanResponseDTO;
import com.app.bibliotecaAPI.loan.dto.UserLoanStatsDTO;
import com.app.bibliotecaAPI.loan.model.Loan;
import com.app.bibliotecaAPI.loan.model.LoanStatus;
import com.app.bibliotecaAPI.loan.repository.LoanRepository;
import com.app.bibliotecaAPI.user.dto.UserResponseDTO;
import com.app.bibliotecaAPI.user.model.User;
import com.app.bibliotecaAPI.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public LoanService(LoanRepository loanRepository, BookRepository bookRepository, UserRepository userRepository) {
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public LoanResponseDTO createLoan(LoanRequestDTO body) {

        Book book = bookRepository.findById(body.book().id())
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        User user = userRepository.findById(body.user().id())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (book.getStatus() != BookStatus.AVAILABLE) {
            throw new IllegalStateException("O livro não está disponível para empréstimo");
        }

        Loan loan = new Loan();
        loan.setLoanDate(LocalDate.now());
        loan.setReturnDate(body.returnDate());
        loan.setBook(book);
        loan.setUser(user);
        loan.setStatus(LoanStatus.ACTIVE);

        book.setStatus(BookStatus.BORROWED);
        bookRepository.save(book);

        loan = loanRepository.save(loan);

        return new LoanResponseDTO(
                loan.getId(),
                new BookResponseDTO(loan.getBook()),
                new UserResponseDTO(loan.getUser()),
                loan.getLoanDate(),
                loan.getReturnDate(),
                loan.getStatus()
        );

    }

    public LoanResponseDTO updateLoan(Long loanId) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Empréstimo não encontrado"));

        if (loan.getStatus() != LoanStatus.ACTIVE) {
            throw new RuntimeException("Este empréstimo não ativo");
        }

        loan.setStatus(LoanStatus.FINISHED);
        loan.setReturnDate(LocalDate.now());

        Book book = loan.getBook();
        book.setStatus(BookStatus.AVAILABLE);
        bookRepository.save(book);

        loanRepository.save(loan);

        return new LoanResponseDTO(
                loan.getId(),
                new BookResponseDTO(loan.getBook()),
                new UserResponseDTO(loan.getUser()),
                loan.getLoanDate(),
                loan.getReturnDate(),
                loan.getStatus()
        );

    }

    public List<LoanResponseDTO> getAllLoans() {

        List<Loan> allLoansActive = loanRepository.findAll();

        return allLoansActive.stream()
                .filter(loan -> loan.getStatus() == LoanStatus.ACTIVE)
                .map(loan -> new LoanResponseDTO(
                        loan.getId(),
                        new BookResponseDTO(loan.getBook()),
                        new UserResponseDTO(loan.getUser()),
                        loan.getLoanDate(),
                        loan.getReturnDate(),
                        loan.getStatus()
                ))
                .collect(Collectors.toList());
    }

    public List<BookLoanStatsDTO> getMostLoanedBooks() {
        List<Object[]> results = loanRepository.findMostLoanedBooks();

        return results.stream()
                .map(result -> {
                    Book book = (Book) result[0];
                    Long loanCount = (Long) result[1];
                    return new BookLoanStatsDTO(
                            new BookResponseDTO(book),
                            book.getTitle(),
                            loanCount
                    );
                })
                .collect(Collectors.toList());
    }

    public List<UserLoanStatsDTO> getMostUsersLoans() {
        List<Object[]> results = loanRepository.findMostUsersLoans();

        return results.stream()
                .map(result -> {
                    User user = (User) result[0];
                    Long loanCount = (Long) result[1];
                    return new UserLoanStatsDTO(
                            new UserResponseDTO(user),
                            loanCount
                    );
                })
                .collect(Collectors.toList());
    }

}
