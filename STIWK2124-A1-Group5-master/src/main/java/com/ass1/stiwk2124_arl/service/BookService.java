package com.ass1.stiwk2124_arl.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ass1.stiwk2124_arl.model.Book;

public interface BookService {

    String saveBook(Book book);

    Page<Book> getAllBooks(Pageable pageable);

    List<Book> searchBooks(String keyword);

    Book getBookById(Long id);

    String updateBook(Long id, Book newBook);

    String deleteBook(Long id);
}
