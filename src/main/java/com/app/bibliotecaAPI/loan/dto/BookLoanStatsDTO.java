package com.app.bibliotecaAPI.loan.dto;

import com.app.bibliotecaAPI.book.dto.BookResponseDTO;

public record BookLoanStatsDTO(
        BookResponseDTO book,
        String title,
        Long loanCount
) {
}
