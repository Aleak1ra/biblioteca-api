package com.app.bibliotecaAPI.loan.dto;

import com.app.bibliotecaAPI.book.dto.BookResponseDTO;
import com.app.bibliotecaAPI.user.dto.UserResponseDTO;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record LoanRequestDTO(
        @NotNull(message = "Livro não pode ser vazio ou nulo") BookResponseDTO book,
        @NotNull(message = "Usuário não pode ser vazio ou nulo") UserResponseDTO user,
        LocalDate returnDate,
        String status) {
}

