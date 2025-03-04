package com.app.bibliotecaAPI.loan.dto;

import com.app.bibliotecaAPI.book.dto.BookResponseDTO;
import com.app.bibliotecaAPI.loan.model.LoanStatus;
import com.app.bibliotecaAPI.user.dto.UserResponseDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public record LoanResponseDTO(
        Long id,
        BookResponseDTO book,
        UserResponseDTO user,
        LocalDate loanDate,
        LocalDate returnDate,
        LoanStatus status
) {
}
