package org.springproject.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springproject.library.entity.BookEntity;
import org.springproject.library.repository.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public BookEntity addBook(BookEntity book) {
        return bookRepository.save(book);
    }

    public Optional<BookEntity> getBookById(String id) {
        return bookRepository.findById(id);
    }

    public List<BookEntity> getAllBooks() {
        return bookRepository.findAll();
    }

    public BookEntity updateBook(BookEntity book) {
        return bookRepository.save(book);
    }

}
