package com.app.bibliotecaAPI.loan.model;

import com.app.bibliotecaAPI.book.model.Book;
import com.app.bibliotecaAPI.user.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "loans")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    @NotNull(message = "O livro não pode ser nulo")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull(message = "O usuário não pode ser nulo")
    private User user;

    @NotNull(message = "A data de empréstimo não pode ser nula")
    @Column(nullable = false)
    private LocalDateTime loanDate;

    @NotNull(message = "A data de devolução não pode ser nula")
    @Column(nullable = false)
    private Date returnDate;

    @NotNull(message = "O status do empréstimo não pode ser nulo")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private LoanStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDateTime loanDate) {
        this.loanDate = loanDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public LoanStatus getStatus() {
        return status;
    }

    public void setStatus(LoanStatus status) {
        this.status = status;
    }
}