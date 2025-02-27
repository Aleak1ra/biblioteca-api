package com.app.bibliotecaAPI.loan.dto;

import com.app.bibliotecaAPI.book.dto.BookResponseDTO;
import com.app.bibliotecaAPI.user.dto.UserResponseDTO;

import java.util.Date;

public record LoanDTO(
        Long id,
        BookResponseDTO book,
        UserResponseDTO user,
        Date loanDate,
        Date returnDate,
        String status
) {
}
