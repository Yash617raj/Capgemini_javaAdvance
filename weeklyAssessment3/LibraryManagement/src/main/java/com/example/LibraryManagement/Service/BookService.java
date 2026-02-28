package com.example.LibraryManagement.Service;

import com.example.LibraryManagement.Exceptions.BookNotFoundException;
import com.example.LibraryManagement.Model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public List<Book> getBooks(){
        return books;
    }

    public Book getBooksById(Long id){
        return books.stream()
                .filter(b-> b.getId().equals(id)).findFirst()
                .orElseThrow(() -> new BookNotFoundException("Book with ID " + id + " not found"));
    }

    public void deleteBook(Long id){
        Book bookToDelete = books.stream()
                .filter(book -> book.getId().equals(id)).findFirst()
                .orElseThrow(() -> new BookNotFoundException("Book with ID " + id + " not found"));

        books.remove(bookToDelete);
    }
}
