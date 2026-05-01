package com.ass1.stiwk2124_arl.service.impl;

import com.ass1.stiwk2124_arl.model.Book;
import com.ass1.stiwk2124_arl.repository.BookRepository;
import com.ass1.stiwk2124_arl.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository repo;

    public BookServiceImpl(BookRepository repo) {
        this.repo = repo;
    }

    // CREATE
    @Override
    public String saveBook(Book book) {
        repo.save(book);
        return "Success";
    }

    // GET ALL (PAGINATION)
    @Override
    public Page<Book> getAllBooks(Pageable pageable) {
        return repo.findAll(pageable);
    }

    // SEARCH
    @Override
    public List<Book> searchBooks(String keyword) {
        return repo.searchBooks(keyword);
    }

    // GET BY ID
    @Override
    public Book getBookById(Long id) {
        if (repo.findById(id).isEmpty()) {
            throw new RuntimeException("Book not found");
        }
        return repo.findById(id).get();
    }

    // UPDATE
    @Override
    public String updateBook(Long id, Book newBook) {
        Optional<Book> existing = repo.findById(id);

        if (existing.isEmpty()) {
            throw new RuntimeException("Book not found");
        }

        Book book = existing.get();
        book.setTitle(newBook.getTitle());
        book.setAuthor(newBook.getAuthor());
        book.setCategory(newBook.getCategory());
        book.setDescription(newBook.getDescription());

        repo.save(book);
        return "Success";
    }

    // DELETE
    @Override
    public String deleteBook(Long id) {
        if (repo.findById(id).isEmpty()) {
            throw new RuntimeException("Book not found");
        }

        repo.deleteById(id);
        return "Success";
    }
}
