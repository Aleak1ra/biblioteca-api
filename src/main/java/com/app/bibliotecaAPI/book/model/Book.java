package com.app.bibliotecaAPI.book.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O título do livro não pode ser nulo ou vazio")
    @Column(length = 100, nullable = false)
    private String title;

    @NotBlank(message = "O nome do autor não pode ser nulo ou vazio")
    @Column(length = 100, nullable = false)
    private String author;

    @NotBlank(message = "O ISBN não pode ser nulo ou vazio")
    @Column(length = 100, nullable = false)
    private String isbn;

    @Enumerated(EnumType.STRING)
    private BookStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }
}