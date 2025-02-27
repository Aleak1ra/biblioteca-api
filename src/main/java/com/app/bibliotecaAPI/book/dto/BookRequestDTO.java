package com.app.bibliotecaAPI.book.dto;

import com.app.bibliotecaAPI.book.model.BookStatus;

public record BookRequestDTO(String title, String author, String isbn, BookStatus status) {
}
