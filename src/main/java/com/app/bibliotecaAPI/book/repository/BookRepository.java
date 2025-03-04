package com.app.bibliotecaAPI.book.repository;

import com.app.bibliotecaAPI.book.model.Book;
import com.app.bibliotecaAPI.book.model.BookStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Long countByStatus(BookStatus status);

}
