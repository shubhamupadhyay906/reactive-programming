package com.reactive.reactiveproject.service;

import com.reactive.reactiveproject.entity.Book;
import com.reactive.reactiveproject.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Mono<Book> createBook(Book book) {
        return bookRepository.save(book).log();
    }

    @Override
    public Flux<Book> getAllBook() {
        return bookRepository.findAll();
    }

    @Override
    public Mono<Book> getBookById(int bookId) {
        return bookRepository.findById(bookId);
    }

    @Override
    public Mono<Book> update(Book book, int bookId) {
        return null;
    }

    @Override
    public Mono<Void> delete(int bookId) {
        return bookRepository.deleteById(bookId);
    }

    @Override
    public Flux<Book> search(String query) {
        return null;
    }
}
