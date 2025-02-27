package com.app.bibliotecaAPI.book.dto;

import com.app.bibliotecaAPI.book.model.BookStatus;

public record BookResponseDTO(
        Long id,
        String title,
        String author,
        String isbn,
        BookStatus status
) {}