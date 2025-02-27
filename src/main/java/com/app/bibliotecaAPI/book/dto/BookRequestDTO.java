package com.app.bibliotecaAPI.book.dto;

import com.app.bibliotecaAPI.book.model.BookStatus;
import jakarta.validation.constraints.NotBlank;

public record BookRequestDTO(
        @NotBlank(message = "O título do livro não pode ser nulo ou vazio") String title,
        @NotBlank(message = "O nome do autor não pode ser nulo ou vazio") String author,
        @NotBlank(message = "O ISBN não pode ser nulo ou vazio") String isbn,
        BookStatus status) {
}