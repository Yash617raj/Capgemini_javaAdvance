package com.cap.BookStroreRest.Repository;

import com.cap.BookStroreRest.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {
    List<Book> findByAuthor(String author);
}
