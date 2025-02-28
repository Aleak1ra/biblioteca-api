package com.app.bibliotecaAPI.book.service;

import com.app.bibliotecaAPI.book.dto.BookRequestDTO;
import com.app.bibliotecaAPI.book.dto.BookResponseDTO;
import com.app.bibliotecaAPI.book.model.Book;
import com.app.bibliotecaAPI.book.model.BookStatus;
import com.app.bibliotecaAPI.book.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class BookServiceTest {

    @InjectMocks
    private BookService bookService;
    @Mock
    private BookRepository bookRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateBook() {
        BookRequestDTO request = new BookRequestDTO("Título Teste", "Autor Teste", "123456789", BookStatus.AVAILABLE);
        Book savedBook = new Book(1L, "Título Teste", "Autor Teste", "123456789", BookStatus.AVAILABLE);

        when(bookRepository.save(any(Book.class))).thenReturn(savedBook);

        BookResponseDTO response = bookService.createBook(request);

        assertNotNull(response);
        assertEquals(1L, response.id());
        assertEquals("Título Teste", response.title());
        assertEquals("Autor Teste", response.author());
        assertEquals("123456789", response.isbn());
        assertEquals(BookStatus.AVAILABLE, response.status());

        verify(bookRepository, times(1)).save(any(Book.class));
    }

    @Test
    public void testCreateBookInvalidRequest() {

        BookRequestDTO request = new BookRequestDTO(null, "Autor Teste", "", BookStatus.AVAILABLE);

        assertThrows(RuntimeException.class, () -> bookService.createBook(request));

        verify(bookRepository, never()).save(any(Book.class));
    }

    @Test
    public void testUpdateBookRequest() {
        BookRequestDTO request = new BookRequestDTO("Título Teste", "Autor Teste", "123456789", BookStatus.AVAILABLE);
        Book savedBook = new Book(1L, "Título Teste", "Autor Teste", "123456789", BookStatus.AVAILABLE);

        when(bookRepository.save(any(Book.class))).thenReturn(savedBook);
        when(bookRepository.findById(1L)).thenReturn(java.util.Optional.of(savedBook));

        BookResponseDTO response = bookService.updateBook(1L, request);

        assertNotNull(response);
        assertEquals(1L, response.id());
        assertEquals("Título Teste", response.title());
        assertEquals("Autor Teste", response.author());
        assertEquals("123456789", response.isbn());
        assertEquals(BookStatus.AVAILABLE, response.status());

        verify(bookRepository, times(1)).save(any(Book.class));
        verify(bookRepository, times(1)).findById(1L);
    }

    @Test
    public void testUpdateBookInvalidRequest() {
        Long nonExistentId = 999L;
        BookRequestDTO requestValid = new BookRequestDTO("Novo Título", "Novo Autor", "987654321", BookStatus.BORROWED);

        when(bookRepository.findById(nonExistentId)).thenReturn(Optional.empty());

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> bookService.updateBook(nonExistentId, requestValid));

        assertEquals("Livro não encontrado!", thrown.getMessage());

        verify(bookRepository, never()).save(any(Book.class));
        verify(bookRepository, times(1)).findById(nonExistentId);
    }

    @Test
    public void testDeleteBookInvalidRequest() {
        Long nonExistentId = 999L;

        when(bookRepository.findById(nonExistentId)).thenReturn(Optional.empty());
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> bookService.deleteBook(nonExistentId));

        verify(bookRepository, never()).delete(any(Book.class));
        verify(bookRepository, times(1)).findById(nonExistentId);
    }

    @Test
    public void testListAllBooks() {
        Book book1 = new Book(1L, "Título 1", "Autor 1", "123456789", BookStatus.AVAILABLE);
        Book book2 = new Book(2L, "Título 2", "Autor 2", "987654321", BookStatus.BORROWED);
        Book book3 = new Book(3L, "Título 3", "Autor 3", "123456789", BookStatus.AVAILABLE);
        Book book4 = new Book(4L, "Título 4", "Autor 4", "987654321", BookStatus.BORROWED);

        when(bookRepository.findAll()).thenReturn(List.of(book1, book2, book3, book4));

        List<BookResponseDTO> response = bookService.getAllBooks();

        assertNotNull(response);
        assertEquals(4, response.size());

        assertEquals(1L, response.get(0).id());
        assertEquals("Título 1", response.get(0).title());
        assertEquals("Autor 1", response.get(0).author());
        assertEquals("123456789", response.get(0).isbn());
        assertEquals(BookStatus.AVAILABLE, response.get(0).status());

        assertEquals(2L, response.get(1).id());
        assertEquals("Título 2", response.get(1).title());
        assertEquals("Autor 2", response.get(1).author());
        assertEquals("987654321", response.get(1).isbn());
        assertEquals(BookStatus.BORROWED, response.get(1).status());

        assertEquals(3L, response.get(2).id());
        assertEquals("Título 3", response.get(2).title());
        assertEquals("Autor 3", response.get(2).author());
        assertEquals("123456789", response.get(2).isbn());
        assertEquals(BookStatus.AVAILABLE, response.get(2).status());

        assertEquals(4L, response.get(3).id());
        assertEquals("Título 4", response.get(3).title());
        assertEquals("Autor 4", response.get(3).author());
        assertEquals("987654321", response.get(3).isbn());
        assertEquals(BookStatus.BORROWED, response.get(3).status());

        verify(bookRepository, times(1)).findAll();
    }

    @Test
    public void testListAllBooksFailed() {
        when(bookRepository.findAll()).thenReturn(List.of());

        List<BookResponseDTO> response = bookService.getAllBooks();

        assertNotNull(response);
        assertEquals(0, response.size());

        verify(bookRepository, times(1)).findAll();
    }

    @Test
    public void testCountAvailableBooks() {

        when(bookRepository.countByStatus(BookStatus.AVAILABLE)).thenReturn(2L);

        Long response = bookService.countAvailableBooks();

        assertNotNull(response);
        assertEquals(2L, response);

        verify(bookRepository, times(1)).countByStatus(BookStatus.AVAILABLE);
    }

    @Test
    public void testCountAvailableBooksFailed() {
        when(bookRepository.countByStatus(BookStatus.AVAILABLE)).thenReturn(0L);

        Long response = bookService.countAvailableBooks();

        assertNotNull(response);
        assertEquals(0, response);

        verify(bookRepository, times(1)).countByStatus(BookStatus.AVAILABLE);
    }
}
