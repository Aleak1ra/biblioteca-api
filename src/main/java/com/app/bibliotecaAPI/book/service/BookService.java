package com.app.bibliotecaAPI.book.service;

import com.app.bibliotecaAPI.book.dto.BookRequestDTO;
import com.app.bibliotecaAPI.book.dto.BookResponseDTO;
import com.app.bibliotecaAPI.book.model.Book;
import com.app.bibliotecaAPI.book.model.BookStatus;
import com.app.bibliotecaAPI.book.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookResponseDTO createBook(BookRequestDTO bookRequestDTO) {
        Book newBook = new Book();
        newBook.setTitle(bookRequestDTO.title());
        newBook.setAuthor(bookRequestDTO.author());
        newBook.setIsbn(bookRequestDTO.isbn());
        newBook.setStatus(bookRequestDTO.status());

        Book savedBook = bookRepository.save(newBook);
        return new BookResponseDTO(
                savedBook.getId(),
                savedBook.getTitle(),
                savedBook.getAuthor(),
                savedBook.getIsbn(),
                savedBook.getStatus()
        );
    }


    public BookResponseDTO updateBook(Long id, BookRequestDTO bookRequestDTO) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado!"));

        existingBook.setTitle(bookRequestDTO.title());
        existingBook.setAuthor(bookRequestDTO.author());
        existingBook.setIsbn(bookRequestDTO.isbn());
        existingBook.setStatus(bookRequestDTO.status());

        Book updatedBook = bookRepository.save(existingBook);
        return new BookResponseDTO(
                updatedBook.getId(),
                updatedBook.getTitle(),
                updatedBook.getAuthor(),
                updatedBook.getIsbn(),
                updatedBook.getStatus()
        );
    }


    public void deleteBook(Long id) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado!"));

        bookRepository.delete(existingBook);
    }


    public BookResponseDTO getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado!"));

        return new BookResponseDTO(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getIsbn(),
                book.getStatus()
        );
    }

    public List<BookResponseDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(book -> new BookResponseDTO(
                        book.getId(),
                        book.getTitle(),
                        book.getAuthor(),
                        book.getIsbn(),
                        book.getStatus()
                ))
                .toList();
    }

    public Long countAvailableBooks() {
        return bookRepository.countByStatus(BookStatus.AVAILABLE);
    }

}



