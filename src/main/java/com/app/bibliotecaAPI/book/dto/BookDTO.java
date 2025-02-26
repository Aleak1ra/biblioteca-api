package com.app.bibliotecaAPI.book.dto;

public record BookDTO(
        Long id,
        String title,
        String author,
        String isbn,
        String status
) {}