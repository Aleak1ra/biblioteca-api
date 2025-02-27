package com.app.bibliotecaAPI.book.dto;

import com.app.bibliotecaAPI.book.model.Book;
import com.app.bibliotecaAPI.book.model.BookStatus;

public record BookResponseDTO(
        Long id,
        String title,
        String author,
        String isbn,
        BookStatus status
) {

    public BookResponseDTO (Book book) {
        this(book.getId(), book.getTitle(), book.getAuthor(), book.getIsbn(), book.getStatus());
    }
}