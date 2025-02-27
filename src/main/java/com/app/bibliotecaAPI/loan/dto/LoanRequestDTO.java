package com.app.bibliotecaAPI.loan.dto;

import com.app.bibliotecaAPI.book.dto.BookResponseDTO;
import com.app.bibliotecaAPI.user.dto.UserResponseDTO;

import java.util.Date;

public record LoanRequestDTO(BookResponseDTO book, UserResponseDTO user, Date loanDate, Date returnDate,
                             String status) {
}
