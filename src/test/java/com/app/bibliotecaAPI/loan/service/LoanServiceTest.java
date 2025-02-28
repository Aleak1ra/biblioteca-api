package com.app.bibliotecaAPI.loan.service;

import com.app.bibliotecaAPI.book.model.Book;
import com.app.bibliotecaAPI.loan.dto.BookLoanStatsDTO;
import com.app.bibliotecaAPI.loan.dto.UserLoanStatsDTO;
import com.app.bibliotecaAPI.loan.repository.LoanRepository;
import com.app.bibliotecaAPI.user.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class LoanServiceTest {

    @Mock
    private LoanRepository loanRepository;

    @InjectMocks
    private LoanService loanService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetMostLoanedBooks() {
        Book book1 = new Book(1L, "Livro 1", "Autor 1", "123456789", null);
        Book book2 = new Book(2L, "Livro 2", "Autor 2", "987654321", null);
        Long loanCount1 = 5L;
        Long loanCount2 = 3L;

        List<Object[]> mockResults = Arrays.asList(
                new Object[]{book1, loanCount1},
                new Object[]{book2, loanCount2}
        );

        when(loanRepository.findMostLoanedBooks()).thenReturn(mockResults);

        List<BookLoanStatsDTO> result = loanService.getMostLoanedBooks();

        assertNotNull(result);
        assertEquals(2, result.size());

        BookLoanStatsDTO stats1 = result.get(0);
        assertEquals("Livro 1", stats1.title());
        assertEquals(5L, stats1.loanCount());
        assertEquals("Livro 1", stats1.book().title());

        BookLoanStatsDTO stats2 = result.get(1);
        assertEquals("Livro 2", stats2.title());
        assertEquals(3L, stats2.loanCount());
        assertEquals("Livro 2", stats2.book().title());

        verify(loanRepository, times(1)).findMostLoanedBooks();
    }

    @Test
    public void testGetMostUsersLoans() {
        User user1 = new User(1L, "Usuário 1", "usuario_teste@gotmail.com");
        User user2 = new User(2L, "Usuário 2", "usuario_teste2@gotmaiul.com");
        Long loanCount1 = 5L;
        Long loanCount2 = 3L;

        List<Object[]> mockResults = Arrays.asList(
                new Object[]{user1, loanCount1},
                new Object[]{user2, loanCount2}
        );

        when(loanRepository.findMostUsersLoans()).thenReturn(mockResults);

        List<UserLoanStatsDTO> result = loanService.getMostUsersLoans();

        assertNotNull(result);
        assertEquals(2, result.size());

        UserLoanStatsDTO stats1 = result.get(0);
        assertEquals("Usuário 1", stats1.user().name());
        assertEquals(5L, stats1.loanCount());

        UserLoanStatsDTO stats2 = result.get(1);
        assertEquals("Usuário 2", stats2.user().name());
        assertEquals(3L, stats2.loanCount());

        verify(loanRepository, times(1)).findMostUsersLoans();
    }
}
