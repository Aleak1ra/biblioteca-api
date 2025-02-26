package com.app.bibliotecaAPI.loan.model;

import com.app.bibliotecaAPI.book.model.Book;
import com.app.bibliotecaAPI.user.model.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
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
    private Book book;

    @ManyToOne
    private User user;

    private Date loanDate;
    private Date returnDate;

    @Enumerated(EnumType.STRING)
    private LoanStatus status;
}